package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.component.UserLoggedStatusPanel;
import net.cassiolandim.biomedcalib.web.BiomedcalibSession;
import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumListPage;
import net.cassiolandim.biomedcalib.web.page.measure.MeasureListPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Cassio Landim
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
    	add(new Link<AdminHomePage>("adminHomeLink"){
			@Override
			public void onClick() {
				setResponsePage(AdminHomePage.class);
			}
			@Override
			public boolean isVisible() {
				return BiomedcalibSession.get().isAdmin();
			}
        });
    	
    	add(new Link<ControlSerumListPage>("controlSerumListLink"){
			@Override
			public void onClick() {
				setResponsePage(ControlSerumListPage.class);
			}
        });
    	
    	add(new Link<MeasureListPage>("measureListLink"){
			@Override
			public void onClick() {
				setResponsePage(MeasureListPage.class);
			}
        });
    	
    	add(new UserLoggedStatusPanel("userStatus"));
    }
}
