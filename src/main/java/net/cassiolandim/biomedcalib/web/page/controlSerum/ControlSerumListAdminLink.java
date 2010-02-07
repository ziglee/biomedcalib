package net.cassiolandim.biomedcalib.web.page.controlSerum;

import org.apache.wicket.markup.html.link.Link;

public class ControlSerumListAdminLink extends Link<ControlSerumListAdminPage> {

	public ControlSerumListAdminLink(String id) {
		super(id);
	}
	
	@Override
	public void onClick() {
		setResponsePage(ControlSerumListAdminPage.class);
	}
}
