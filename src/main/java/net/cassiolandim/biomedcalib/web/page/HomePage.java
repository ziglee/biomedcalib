package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.measure.MeasureListPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Cassio Landim
 */
public class HomePage extends BasePage {

	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	add(new Link<AdminHomePage>("adminHomeLink"){
			@Override
			public void onClick() {
				setResponsePage(AdminHomePage.class);
			}
        });
    	
    	add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
    }
}
