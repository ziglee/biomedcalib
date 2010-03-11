package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.component.MeasuresAggregateHistogramChartImage;
import net.cassiolandim.biomedcalib.component.MeasuresAggregateLineChartImage;
import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;
import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumEditPage;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Cassio Landim
 */
public class MeasureDetailsPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureDetailsPage(Long measuresAggregateId) {
		final MeasuresAggregate measuresAggregate = measuresAggregatePersistableService.find(measuresAggregateId);
		EntityLoadableDetachableModel<MeasuresAggregate> detachModel = new EntityLoadableDetachableModel<MeasuresAggregate>(measuresAggregate, measuresAggregatePersistableService);
		CompoundPropertyModel<MeasuresAggregate> compoundModel = new CompoundPropertyModel<MeasuresAggregate>(detachModel);
		this.setDefaultModel(compoundModel);
		
		add(new Label("laboratory.name"));
		add(new Label("observation"));
		add(new Label("mean", new PropertyModel<MeasuresAggregate>(detachModel, "mean")));
		add(new Label("standardDeviation", new PropertyModel<MeasuresAggregate>(detachModel, "standardDeviation")));
		add(new Label("coefficientOfVariation", new PropertyModel<MeasuresAggregate>(detachModel, "cofficientOfVariation")));
		
		Link<ControlSerumEditPage> controlSerumEditLink = new Link<ControlSerumEditPage>("controlSerumEditLink"){
			@Override
			public void onClick() {
				setResponsePage(new ControlSerumEditPage(measuresAggregate.getControlSerum()));
			}
		};
		add(controlSerumEditLink);
		
		controlSerumEditLink.add(new Label("controlSerum.manufacturer"));
		controlSerumEditLink.add(new Label("controlSerum.name"));
		
		Label labelManufacturer = new Label("labelManufacturer");
		labelManufacturer.setDefaultModel(new ResourceModel("inUse"));
		add(labelManufacturer);
		
		Label labelInPreparation = new Label("labelInPreparation");
		labelInPreparation.setDefaultModel(new ResourceModel("current"));
		add(labelInPreparation);
		
		if(measuresAggregate.getControlSerum().getStatus().equals(ControlSerum.STATUS_PREP)){
			labelManufacturer.setDefaultModel(new ResourceModel("manufacturer"));
			labelInPreparation.setDefaultModel(new ResourceModel("inPreparation"));
		}
		
		add(new Label("controlSerum.mean"));
		add(new Label("controlSerum.standardDeviation"));
		add(new Label("controlSerum.coefficientOfVariation"));
		add(new Label("controlSerum.statusString"));
		
		MeasuresAggregateLineChartImage measuresAggregateLineChartImage = new MeasuresAggregateLineChartImage("lineChart", measuresAggregateId);
		add(measuresAggregateLineChartImage);
		MeasuresAggregateHistogramChartImage measuresAggregateHistogramChartImage = new MeasuresAggregateHistogramChartImage("histogramChart", measuresAggregateId);
		add(measuresAggregateHistogramChartImage);
		
		if(measuresAggregate.getMeasures().size() == 0){
			measuresAggregateLineChartImage.setVisible(false);
			measuresAggregateHistogramChartImage.setVisible(false);
		}

		DataView<Measure> dataView = new DataView<Measure>("measures", new ListDataProvider<Measure>(measuresAggregate.getMeasures())) {
			@Override
			protected void populateItem(final Item<Measure> item){
				final Measure measure = (Measure)item.getModelObject();
				CompoundPropertyModel<Measure> compoundModel = new CompoundPropertyModel<Measure>(measure);
				item.setModel(compoundModel);
				item.add(new Label("index", Integer.toString(item.getIndex() + 1)));
				item.add(new Label("date"));
				item.add(new Label("value"));
			}
		};
		add(dataView);
    	
    	add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
    	
    	add(new Link<MeasureListPage>("editLink"){
			@Override
			public void onClick() {
				setResponsePage(new MeasureDetailsEditPage(measuresAggregate.getId()));
			}
        });

    	Link<MeasureListPage> archiveLink = new Link<MeasureListPage>("archiveLink"){
			@Override
			public void onClick() {
				measuresAggregatePersistableService.archive(measuresAggregate);
			}
			@Override
			public boolean isVisible() {
				return measuresAggregate.getActive();
			}
        };
		add(archiveLink);
		
    	Link<MeasureListPage> unarchiveLink = new Link<MeasureListPage>("unarchiveLink"){
			@Override
			public void onClick() {
				measuresAggregatePersistableService.unarchive(measuresAggregate);
			}
			@Override
			public boolean isVisible() {
				return !measuresAggregate.getActive();
			}
        };
		add(unarchiveLink);
		
    	Link<MeasureListPage> deleteLink = new Link<MeasureListPage>("deleteLink"){
			@Override
			public void onClick() {
				measuresAggregatePersistableService.remove(measuresAggregate);
				setResponsePage(MeasureListPage.class);
			}
        };
		deleteLink.add(new AttributeAppender("onclick", new Model("return confirm('Deseja realmente excluir?');"), ";"));
		add(deleteLink);

		addHomeLink();
	}
}
