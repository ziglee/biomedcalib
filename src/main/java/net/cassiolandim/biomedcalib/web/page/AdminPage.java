package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListPage;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.link.Link;

public class AdminPage extends BasePage {

	public AdminPage(PageParameters pageParameters) {
		add(new Link("labListLink"){
			@Override
			public void onClick() {
				setResponsePage(LaboratoryListPage.class);
			}
		});
	}
}
