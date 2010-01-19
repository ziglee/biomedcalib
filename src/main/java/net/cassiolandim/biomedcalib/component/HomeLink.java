package net.cassiolandim.biomedcalib.component;

import net.cassiolandim.biomedcalib.web.page.HomePage;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * @author Cassio Landim
 */
public class HomeLink extends BookmarkablePageLink<HomePage> {

	public HomeLink() {
		super("homeLink", HomePage.class);
	}
}
