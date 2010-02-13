package net.cassiolandim.biomedcalib.web.page.laboratory;

import org.apache.wicket.markup.html.link.Link;

/**
 * @author Cassio Landim
 */
public class LaboratoryListLink extends Link<LaboratoryListPage> {

	public LaboratoryListLink(String id) {
		super(id);
	}
	
	@Override
	public void onClick() {
		setResponsePage(LaboratoryListPage.class);
	}
}
