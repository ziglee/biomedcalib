package net.cassiolandim.biomedcalib.component;

import net.cassiolandim.biomedcalib.web.page.AdminHomePage;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * @author Cassio Landim
 */
public class AdminHomeLink extends BookmarkablePageLink<AdminHomePage> {

	public AdminHomeLink() {
		super("adminHomeLink", AdminHomePage.class);
	}
}
