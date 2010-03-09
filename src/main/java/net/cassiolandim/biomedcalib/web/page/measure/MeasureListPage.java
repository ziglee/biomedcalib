package net.cassiolandim.biomedcalib.web.page.measure;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.service.MeasuresAggregatePersistableService;
import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Cassio Landim
 */
public class MeasureListPage extends BasePage {

	@SpringBean(name = "measuresAggregatePersistableService")
	private MeasuresAggregatePersistableService measuresAggregatePersistableService;
	
	public MeasureListPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		EntityListLoadableDetachableModel<MeasuresAggregate, List<MeasuresAggregate>> entityListLoadableDetachableModel = new EntityListLoadableDetachableModel<MeasuresAggregate, List<MeasuresAggregate>>(measuresAggregatePersistableService){
			@Override
			protected List<MeasuresAggregate> load() {
				return measuresAggregatePersistableService.findByLaboratory(BiomedcalibSession.get().getLaboratory());
			}
		};
		
		add(new ListView<MeasuresAggregate>("measureAggregates", entityListLoadableDetachableModel){
			@Override
			protected void populateItem(ListItem<MeasuresAggregate> item){
				final MeasuresAggregate measureAggregate = item.getModelObject();
				CompoundPropertyModel<MeasuresAggregate> compoundModel = new CompoundPropertyModel<MeasuresAggregate>(measureAggregate);
				item.setModel(compoundModel);
				
				item.add(new Label("firstDate"));
				item.add(new Label("lastDate"));
				item.add(new Label("controlSerum.manufacturer"));
				item.add(new Label("controlSerum.name"));
				
				item.add(new Link<MeasureDetailsPage>("measureDetailsLink"){
					@Override
					public void onClick() {
						setResponsePage(new MeasureDetailsPage(measureAggregate.getId()));
					}
		        });
			}
		});
		
		add(new Link<MeasureDetailsPage>("newLink"){
			@Override
			public void onClick() {
				setResponsePage(new MeasureDetailsEditPage());
			}
        });
		
		addHomeLink();
	}
}
