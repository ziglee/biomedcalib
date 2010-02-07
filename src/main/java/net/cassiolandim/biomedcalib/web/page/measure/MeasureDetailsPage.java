package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.component.MeasuresAggregateHistogramChartImage;
import net.cassiolandim.biomedcalib.component.MeasuresAggregateLineChartImage;
import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;
import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumEditPage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasureDetailsPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureDetailsPage(Long measuresAggregateId) {
		final MeasuresAggregate measuresAggregate = measuresAggregatePersistableService.find(measuresAggregateId);
		EntityLoadableDetachableModel<MeasuresAggregate> detachModel = new EntityLoadableDetachableModel<MeasuresAggregate>(measuresAggregate, measuresAggregatePersistableService);
		CompoundPropertyModel<MeasuresAggregate> compoundModel = new CompoundPropertyModel<MeasuresAggregate>(detachModel);
		this.setDefaultModel(compoundModel);
		
		add(new Label("creationDate"));
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
		
		add(new Label("controlSerum.name"));
		add(new Label("controlSerum.mean"));
		add(new Label("controlSerum.minimum"));
		add(new Label("controlSerum.maximum"));
		add(new Label("controlSerum.standardDeviation"));
		add(new Label("controlSerum.coefficientOfVariation"));
		add(new Label("controlSerum.statusString"));
		
		add(new MeasuresAggregateLineChartImage("lineChart", measuresAggregateId));
		add(new MeasuresAggregateHistogramChartImage("histogramChart", measuresAggregateId));

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

		addHomeLink();
	}
}
