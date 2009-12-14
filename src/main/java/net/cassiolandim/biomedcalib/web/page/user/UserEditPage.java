package net.cassiolandim.biomedcalib.web.page.user;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.LaboratorySimplePersistableService;
import net.cassiolandim.biomedcalib.service.UserSimplePersistableService;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.model.LaboratoryListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class UserEditPage extends AdminBasePage {

	@SpringBean(name = "laboratorySimplePersistableService")
	private LaboratorySimplePersistableService laboratorySimplePersistableService;
	
	@SpringBean(name = "userSimplePersistableService")
	private UserSimplePersistableService userSimplePersistableService;
	
	public UserEditPage() {
		this(new User());
	}
	
	public UserEditPage(final User user) {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		final EntityLoadableDetachableModel<User> model = new EntityLoadableDetachableModel<User>(user, userSimplePersistableService);
		
		Form<User> form = new Form<User>("form", model);
		add(form);
		
		ChoiceRenderer<Laboratory> choiceRenderer = new ChoiceRenderer<Laboratory>("name","id");
		DropDownChoice<Laboratory> labs = new DropDownChoice<Laboratory>("laboratory", new PropertyModel<Laboratory>(user, "laboratory"), new LaboratoryListLoadableDetachableModel(laboratorySimplePersistableService));
		labs.setRequired(true);
		labs.setChoiceRenderer(choiceRenderer);
		form.add(labs);
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(user, "name"));
		name.setLabel(new ResourceModel("name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(50));
		form.add(name);
		
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				userSimplePersistableService.save(user);
				model.setId(user.getId());
				info(getString("user.save.success"));
			}
		};
		form.add(save);
		
		add(new UserListLink("listLink"));
		addAdminHomeLink();
	}
}
