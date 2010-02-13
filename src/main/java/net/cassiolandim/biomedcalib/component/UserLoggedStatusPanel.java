package net.cassiolandim.biomedcalib.component;

import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.page.signInOut.SignInPage;
import net.cassiolandim.biomedcalib.web.page.signInOut.SignOutPage;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class UserLoggedStatusPanel extends Panel {
	
	public UserLoggedStatusPanel(String id,	Class<? extends Page> logoutPageClass) {
		super(id);
	
		add(new Label("name", new PropertyModel<String>(this, "session.user.name")));
		
		PageParameters parameters = new PageParameters();
		parameters.add(SignOutPage.REDIRECT_TO_PAGE_PARAM, logoutPageClass.getName());

		add(new BookmarkablePageLink<SignOutPage>("signout", SignOutPage.class, parameters) {
			@Override
			public boolean isVisible() {
				return BiomedcalibSession.get().isAuthenticated();
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
