package net.cassiolandim.biomedcalib.web.page.report;

import org.apache.wicket.markup.html.link.Link;

public class ReportFilterLink extends Link<ReportFilterPage> {

	public ReportFilterLink(String id) {
		super(id);
	}
	
	@Override
	public void onClick() {
		setResponsePage(ReportFilterPage.class);
	}

}
