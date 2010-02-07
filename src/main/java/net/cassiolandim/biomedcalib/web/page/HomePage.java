package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.component.UserLoggedStatusPanel;
import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumListPage;
import net.cassiolandim.biomedcalib.web.page.measure.MeasureListPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

import sun.rmi.log.LogOutputStream;

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
    	
    	add(new UserLoggedStatusPanel("userStatus", HomePage.class));
    }
}
