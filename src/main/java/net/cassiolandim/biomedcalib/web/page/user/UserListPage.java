package net.cassiolandim.biomedcalib.web.page.user;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserPersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
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
public class UserListPage extends AdminBasePage {

	@SpringBean(name = "userPersistableService")
	private UserPersistableService userPersistableService;
	
	public UserListPage() {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		add(new ListView<User>("users", new EntityListLoadableDetachableModel<User, List<User>>(userPersistableService)){
			@Override
			protected void populateItem(ListItem<User> item){
				final User user = item.getModelObject();
				
				item.add(new Label("name", user.getName()));
				item.add(new Label("laboratory.name", user.getLaboratory().getName()));
				item.add(new Label("active", user.getActive().toString()));
				
				item.add(new Link<UserEditPage>("editLink"){
					@Override
					public void onClick() {
						setResponsePage(new UserEditPage(user));
					}
				});
				
				item.add(new Link<UserListPage>("deleteLink"){
					@Override
					public void onClick() {
						userPersistableService.remove(user);
						info(getString("user.delete.success"));
					}
				});
			}
		});
		
		add(new Link<UserEditPage>("newLink"){
			@Override
			public void onClick() {
				setResponsePage(new UserEditPage());
			}
		});
		
		addAdminHomeLink();
	}
}
