package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumListLink;
import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListLink;
import net.cassiolandim.biomedcalib.web.page.user.UserListLink;

import org.apache.wicket.PageParameters;

/**
 * @author Cassio Landim
 */
public class AdminHomePage extends BasePage {

	public AdminHomePage(PageParameters pageParameters) {
		add(new LaboratoryListLink("labListLink"));
		add(new UserListLink("userListLink"));
		add(new ControlSerumListLink("controlSerumListLink"));
	}
}
