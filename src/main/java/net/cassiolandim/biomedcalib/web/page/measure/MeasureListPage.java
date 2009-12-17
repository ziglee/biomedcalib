package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MeasureListPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureListPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		add(new ListView<MeasuresAggregate>("measureAggregates", new EntityListLoadableDetachableModel<MeasuresAggregate, List<MeasuresAggregate>>(measuresAggregatePersistableService)){
			@Override
			protected void populateItem(ListItem<MeasuresAggregate> item){
				final MeasuresAggregate measureAggregate = item.getModelObject();
				
				CompoundPropertyModel<MeasuresAggregate> compoundModel = new CompoundPropertyModel<MeasuresAggregate>(measureAggregate);
				
				item.setModel(compoundModel);
				
				item.add(new Label("creationDate"));
				item.add(new Label("laboratory.name"));
				item.add(new Label("measures1.controlSerum.name"));
				item.add(new Label("measures2.controlSerum.name"));
				item.add(new Label("measures3.controlSerum.name"));
			}
		});
	}
}
