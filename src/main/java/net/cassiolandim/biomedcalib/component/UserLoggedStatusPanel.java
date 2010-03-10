package net.cassiolandim.biomedcalib.component;

import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.page.HomePage;
import net.cassiolandim.biomedcalib.web.page.signInOut.SignInPage;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class UserLoggedStatusPanel extends Panel {
	
	public UserLoggedStatusPanel(String id) {
		super(id);
	
		add(new Label("name", new PropertyModel<String>(this, "session.user.name")));

		add(new Link<HomePage>("signout") {
			@Override
			public boolean isVisible() {
				return BiomedcalibSession.get().isAuthenticated();
			}

			@Override
			public void onClick() {
				BiomedcalibSession.get().setUser(null);
				BiomedcalibSession.get().invalidate();
				setResponsePage(HomePage.class);
			}
		});

		add(new Link<SignInPage>("signin") {
			@Override
			public void onClick() {
				throw new RestartResponseAtInterceptPageException(SignInPage.class);
			}

			@Override
			public boolean isVisible() {
				return !BiomedcalibSession.get().isAuthenticated();
			}
		});
	}
}
