package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.controlSerum.ControlSerumListAdminLink;
import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListLink;
import net.cassiolandim.biomedcalib.web.page.report.ReportFilterLink;
import net.cassiolandim.biomedcalib.web.page.user.UserListLink;

import org.apache.wicket.PageParameters;

/**
 * @author Cassio Landim
 */
public class AdminHomePage extends AdminBasePage {

	public AdminHomePage(PageParameters pageParameters) {
		add(new LaboratoryListLink("labListLink"));
		add(new UserListLink("userListLink"));
		add(new ControlSerumListAdminLink("controlSerumListLink"));
		add(new ReportFilterLink("reportFilterLink"));
	}
}
