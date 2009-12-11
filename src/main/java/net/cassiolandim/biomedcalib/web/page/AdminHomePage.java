package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author Cassio Landim
 */
public class AdminHomePage extends BasePage {

	public AdminHomePage(PageParameters pageParameters) {
		add(new Link("labListLink"){
			@Override
			public void onClick() {
				setResponsePage(LaboratoryListPage.class);
			}
		});
	}
}
