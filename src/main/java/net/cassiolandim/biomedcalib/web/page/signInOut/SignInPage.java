package net.cassiolandim.biomedcalib.web.page.signInOut;

import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.service.UserPersistableService;
import net.cassiolandim.biomedcalib.util.HashGenerator;
import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.page.BasePage;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class SignInPage extends BasePage {

	public SignInPage() {
		add(new SignInForm("form"));
	}
	
	private static class SignInForm extends StatelessForm {

		@SpringBean(name = "userPersistableService")
		private UserPersistableService userPersistableService;
		
		private String password;
		private String username;

		public SignInForm(String id) {
			super(id);
			setModel(new CompoundPropertyModel(this));
			add(new TextField("username"));
			add(new PasswordTextField("password"));
		}

		@Override
		public final void onSubmit() {
			if (signIn(username, password)) {
				if (!continueToOriginalDestination()) {
					setResponsePage(getApplication().getHomePage());
				}
			} else {
				error("Unknown login/password");
			}
		}

		private boolean signIn(String username, String password) {
			if (username != null && password != null) {
				User user = userPersistableService.findByLogin(username);
				if (user != null) {
					if (user.getPasswordHash().equals(HashGenerator.convert(password))) {
						BiomedcalibSession.get().setUser(user);
						return true;
					}
				}
			}
			return false;
		}
	}
}
