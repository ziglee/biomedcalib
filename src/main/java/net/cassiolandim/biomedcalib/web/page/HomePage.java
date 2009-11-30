package net.cassiolandim.biomedcalib.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

/**
 * Homepage
 * @author Cassio Landim
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        add(new Label("message", "If you see this message wicket is properly configured and running"));
    }
}
