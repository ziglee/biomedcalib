package net.cassiolandim.biomedcalib.web.page.user;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.LaboratoryPersistableService;
import net.cassiolandim.biomedcalib.service.UserPersistableService;
import net.cassiolandim.biomedcalib.util.HashGenerator;
import net.cassiolandim.biomedcalib.web.model.EntityListLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.model.EntityLoadableDetachableModel;
import net.cassiolandim.biomedcalib.web.page.AdminBasePage;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;

/**
 * @author Cassio Landim
 */
public class UserEditPage extends AdminBasePage {

	@SpringBean(name = "laboratoryPersistableService")
	private LaboratoryPersistableService laboratoryPersistableService;
	
	@SpringBean(name = "userPersistableService")
	private UserPersistableService userPersistableService;
	
	public UserEditPage() {
		this(new User());
	}
	
	public UserEditPage(final User user) {
		FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		add(feedbackPanel);
		
		final EntityLoadableDetachableModel<User> model = new EntityLoadableDetachableModel<User>(user, userPersistableService);
		
		Form<User> form = new Form<User>("form", model);
		add(form);
		
		EntityListLoadableDetachableModel<Laboratory, List<Laboratory>> labListModel = new EntityListLoadableDetachableModel<Laboratory, List<Laboratory>>(laboratoryPersistableService);
		
		ChoiceRenderer<Laboratory> choiceRenderer = new ChoiceRenderer<Laboratory>("name","id");
		DropDownChoice<Laboratory> labs = new DropDownChoice<Laboratory>("laboratory", new PropertyModel<Laboratory>(user, "laboratory"), labListModel);
		labs.setLabel(new ResourceModel("laboratory"));
		labs.setRequired(true);
		labs.setChoiceRenderer(choiceRenderer);
		form.add(labs);
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(user, "name"));
		name.setLabel(new ResourceModel("name"));
		name.setRequired(true);
		name.add(StringValidator.maximumLength(150));
		form.add(name);
		
		TextField<String> login = new TextField<String>("login", new PropertyModel<String>(user, "login"));
		login.setLabel(new ResourceModel("login"));
		login.setRequired(true);
		login.add(StringValidator.maximumLength(30));
		form.add(login);

		Model<String> passwordModel = new Model<String>();
		final PasswordTextField password = new PasswordTextField("password", passwordModel);
		password.setLabel(new ResourceModel("password"));
		password.add(StringValidator.maximumLength(30));
		password.setRequired(false);
		form.add(password);
		
		RadioGroup<Boolean> active = new RadioGroup<Boolean>("active", new PropertyModel<Boolean>(user, "active"));
		active.add(new Radio<Boolean>("true", new Model<Boolean>(true)));
		active.add(new Radio<Boolean>("false", new Model<Boolean>(false)));
		active.setRequired(true);
		form.add(active);
		
		RadioGroup<Boolean> admin = new RadioGroup<Boolean>("admin", new PropertyModel<Boolean>(user, "admin"));
		admin.add(new Radio<Boolean>("true", new Model<Boolean>(true)));
		admin.add(new Radio<Boolean>("false", new Model<Boolean>(false)));
		admin.setRequired(true);
		form.add(admin);
		
		final FileUploadField signature = new FileUploadField("signature");
		form.add(signature);
		  
		Button save = new Button("save"){
			@Override
			public void onSubmit() {
				if(signature.getFileUpload()!=null){
					user.setSignature(signature.getFileUpload().getBytes());
				}
				String passwordPlain = password.getInput();
				if(passwordPlain != null && !passwordPlain.equals("")){
					user.setPasswordHash(HashGenerator.convert(passwordPlain));
				}
				userPersistableService.save(user);
				model.setId(user.getId());
				info(getString("user.save.success"));
			}
		};
		form.add(save);
		
		add(new UserListLink("listLink"));
		addAdminHomeLink();
	}
}
