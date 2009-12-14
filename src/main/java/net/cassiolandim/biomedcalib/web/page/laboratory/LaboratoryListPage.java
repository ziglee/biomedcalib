package net.cassiolandim.biomedcalib.web.page.laboratory;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.web.model.LaboratoryListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author Cassio Landim
 */
public class LaboratoryListPage extends AdminBasePage {

	@SpringBean(name = "laboratoryPersistableService")
	private LaboratoryPersistableService laboratoryPersistableService;
	
	public LaboratoryListPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		add(new ListView<Laboratory>("laboratories", new LaboratoryListLoadableDetachableModel(laboratoryPersistableService)){
			@Override
			protected void populateItem(ListItem<Laboratory> item){
				final Laboratory laboratory = item.getModelObject();
				
				item.add(new Label("name", laboratory.getName()));
				
				item.add(new Link<LaboratoryEditPage>("editLink"){
					@Override
					public void onClick() {
						setResponsePage(new LaboratoryEditPage(laboratory));
					}
				});
				
				item.add(new Link<LaboratoryListPage>("deleteLink"){
					@Override
					public void onClick() {
						laboratoryPersistableService.remove(laboratory);
						info(getString("laboratory.delete.success"));
					}
				});
			}
		});
		
		add(new Link<LaboratoryEditPage>("newLink"){
			@Override
			public void onClick() {
				setResponsePage(new LaboratoryEditPage());
			}
		});
		
		addAdminHomeLink();
	}
}
