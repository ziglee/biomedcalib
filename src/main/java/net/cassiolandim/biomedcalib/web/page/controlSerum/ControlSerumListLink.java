package net.cassiolandim.biomedcalib.web.page.controlSerum;

import org.apache.wicket.markup.html.link.Link;

public class ControlSerumListLink extends Link<ControlSerumListPage> {

	public ControlSerumListLink(String id) {
		super(id);
	}
	
	@Override
	public void onClick() {
		setResponsePage(ControlSerumListPage.class);
	}
}
