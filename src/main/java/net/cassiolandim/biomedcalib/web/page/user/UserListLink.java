package net.cassiolandim.biomedcalib.web.page.user;

import org.apache.wicket.markup.html.link.Link;

/**
 * @author Cassio Landim
 */
class UserListLink extends Link<UserListPage> {
	
	public UserListLink(String id) {
		super(id);
	}
	
	@Override
	public void onClick() {
		setResponsePage(UserListPage.class);
	}
}
