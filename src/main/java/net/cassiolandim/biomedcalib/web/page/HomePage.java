package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.measure.NewMeasurePage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * Homepage
 * @author Cassio Landim
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        add(new Link("newMeasureLink"){
			@Override
			public void onClick() {
				setResponsePage(NewMeasurePage.class);
			}
        });
    }
}
