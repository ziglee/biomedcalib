package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
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
		add(new MeasuresPerLevelPanel("measures1", measuresAggregate.getMeasures1()));
		add(new MeasuresPerLevelPanel("measures2", measuresAggregate.getMeasures2()));
		add(new MeasuresPerLevelPanel("measures3", measuresAggregate.getMeasures3()));
	}
}
