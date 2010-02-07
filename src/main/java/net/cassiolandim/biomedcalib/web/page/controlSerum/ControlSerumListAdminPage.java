package net.cassiolandim.biomedcalib.web.page.controlSerum;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.service.ControlSerumPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

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
public class ControlSerumListAdminPage extends AdminBasePage {

	@SpringBean(name = "controlSerumPersistableService")
	private ControlSerumPersistableService controlSerumPersistableService;
	
	public ControlSerumListAdminPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		add(new ListView<ControlSerum>("controlSerums", new EntityListLoadableDetachableModel<ControlSerum, List<ControlSerum>>(controlSerumPersistableService)){
			@Override
			protected void populateItem(ListItem<ControlSerum> item){
				final ControlSerum controlSerum = item.getModelObject();
				
				item.setModel(new CompoundPropertyModel<ControlSerum>(controlSerum));
				item.add(new Label("laboratory.name"));
				item.add(new Label("name"));
				item.add(new Label("minimum"));
				item.add(new Label("maximum"));
				item.add(new Label("mean"));
				item.add(new Label("standardDeviation"));
				item.add(new Label("coefficientOfVariation"));
				item.add(new Label("statusString"));
				
				item.add(new Link<ControlSerumEditAdminPage>("editLink"){
					@Override
					public void onClick() {
						setResponsePage(new ControlSerumEditAdminPage(controlSerum));
					}
				});
				
				item.add(new Link<ControlSerumListAdminPage>("deleteLink"){
					@Override
					public void onClick() {
						controlSerumPersistableService.remove(controlSerum);
						info(getString("controlSerum.delete.success"));
					}
				});
			}
		});
		
		add(new Link<ControlSerumEditAdminPage>("newLink"){
			@Override
			public void onClick() {
				setResponsePage(new ControlSerumEditAdminPage());
			}
		});
		
		addAdminHomeLink();
	}
}
