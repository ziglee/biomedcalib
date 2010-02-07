package net.cassiolandim.biomedcalib.web.page.signInOut;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;

public class SignOutPage extends Page {
	
	public static final String REDIRECTPAGE_PARAM = "redirectpage";

	@SuppressWarnings("unchecked")
	public SignOutPage(final PageParameters parameters) {
		String page = parameters.getString(REDIRECTPAGE_PARAM);
		
		Class<? extends Page> pageClass;
		if (page != null) {
			try {
				pageClass = (Class<? extends Page>) Class.forName(page);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		} else {
			pageClass = getApplication().getHomePage();
		}
		
		getSession().invalidate();
		
		setResponsePage(pageClass);
	}
}
