package net.cassiolandim.biomedcalib.web.page.report;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import net.cassiolandim.biomedcalib.component.MeasuresAggregateLineChartImage;
import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.resource.BufferedDynamicImageResource;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ReportPage extends AdminBasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	private NumberFormat numberFormat;
	Laboratory laboratory;
	
	List<MeasuresAggregate> measuresAggregates = new ArrayList<MeasuresAggregate>();
	
	public ReportPage(List<Long> ids) {
		int biggestMeasuresListSize = 0;
		for(Long id : ids){
			MeasuresAggregate measureAggregate = measuresAggregatePersistableService.find(id);
			measuresAggregates.add(measureAggregate);
			
			List<Measure> measures = measureAggregate.getMeasures();
			int size = measures.size();
			if(size > biggestMeasuresListSize) biggestMeasuresListSize = size;
		}
		
		laboratory = measuresAggregates.get(0).getLaboratory();
		numberFormat = laboratory.getNumberFormat(getLocale());

		addAdminHomeLink();
		addLaboratoryNameAndLogomark();
		addResponsableAnalyst();
		addControlSerums();
		addIndexRows(biggestMeasuresListSize);
		addMeasuresColumns(ids);
		addMeasuresAggregatesRows();
		addChartsViews(ids);
	}

	private void addLaboratoryNameAndLogomark() {
		add(new Label("laboratory", laboratory.getName()));
		
		byte[] logomarkBytes = laboratory.getLogomark();
		if(logomarkBytes != null && logomarkBytes.length > 0){
	        try {
	        	final BufferedDynamicImageResource resource = new BufferedDynamicImageResource(); 
	            java.awt.image.BufferedImage image = ImageIO.read(new ByteArrayInputStream(logomarkBytes));
		        resource.setImage(image); 
		        add(new Image("bothCardImage", resource)); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			add(new Image("bothCardImage", new Model<String>("blank.gif")));
		}
	}

	private void addResponsableAnalyst() {
		Label loggedUser = new Label("loggedUser", new Model<String>(BiomedcalibSession.get().getUser().getName()));
		add(loggedUser);
	}

	private void addControlSerums() {
		ListView<MeasuresAggregate> controlSerumsView = new ListView<MeasuresAggregate>("controlSerums", measuresAggregates) {
			@Override
			protected void populateItem(ListItem<MeasuresAggregate> item){
				final MeasuresAggregate measuresAggregate = item.getModelObject();
				ControlSerum controlSerum = measuresAggregate.getControlSerum();
				
				item.add(new Label("index", Integer.toString(item.getIndex() + 1)));
				item.add(new Label("meanInUse", numberFormat.format(controlSerum.getMean())));
				item.add(new Label("standardDeviationInUse", numberFormat.format(controlSerum.getStandardDeviation())));
				item.add(new Label("meanCurrent", numberFormat.format(measuresAggregate.getMean())));
				item.add(new Label("standardDeviationCurrent", numberFormat.format(measuresAggregate.getStandardDeviation())));
				item.add(new Label("cofficientOfVariationCurrent", numberFormat.format(measuresAggregate.getCofficientOfVariation())));
			}
		};
		add(controlSerumsView);
	}

	private void addIndexRows(int biggestMeasuresListSize) {
		List<Integer> row = new ArrayList<Integer>();
		for(int x = 1; x <= biggestMeasuresListSize; x++){
			row.add(x);
		}
		
		ListView<Integer> indexRows = new ListView<Integer>("indexRows", row) {
			@Override
			protected void populateItem(ListItem<Integer> item) {
				item.add(new Label("label", item.getModel()));
			}
		};
		add(indexRows);
	}

	private void addMeasuresColumns(List<Long> ids) {
		ListView<Long> measuresColumns = new ListView<Long>("measuresColumns", ids) {
			@Override
			protected void populateItem(ListItem<Long> item) {
				item.add(new Label("label", Integer.toString(item.getIndex() + 1)));
			}
		};
		add(measuresColumns);
	}

	private void addMeasuresAggregatesRows() {
		ListView<MeasuresAggregate> measuresAggregatesRows = new ListView<MeasuresAggregate>("measuresAggregates", measuresAggregates) {
			@Override
			protected void populateItem(ListItem<MeasuresAggregate> item) {
				final MeasuresAggregate measuresAggregate = item.getModelObject();
				ListView<Measure> measureRow = new ListView<Measure>("measuresRows", measuresAggregate.getMeasures()) {
					@Override
					protected void populateItem(ListItem<Measure> item) {
						item.add(new Label("label", numberFormat.format(item.getModelObject().getValue())));
					}
				};
				item.add(measureRow);
			}
		};
		add(measuresAggregatesRows);
	}

	private void addChartsViews(List<Long> ids) {
		ListView<Long> chartsViews = new ListView<Long>("chartsView", ids) {
			@Override
			protected void populateItem(ListItem<Long> item) {
				MeasuresAggregateLineChartImage measuresAggregateLineChartImage = new MeasuresAggregateLineChartImage("lineChart", item.getModelObject());
				item.add(measuresAggregateLineChartImage);
			}
		};
		add(chartsViews);
	}
}
