package net.cassiolandim.biomedcalib.web.page.signInOut;

import net.cassiolandim.biomedcalib.web.BiomedcalibSession;

import org.apache.wicket.Page;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

/**
 * @author Cassio Landim
 */
public class SignOutPage extends WebPage {
	
	public static final String REDIRECT_TO_PAGE_PARAM = "redirectpage";

	@SuppressWarnings("unchecked")
	public SignOutPage(final PageParameters parameters) {
		String page = parameters.getString(REDIRECT_TO_PAGE_PARAM);
		
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
		
		BiomedcalibSession.get().setUser(null);
		BiomedcalibSession.get().invalidate();
		
		setResponsePage(pageClass);
	}
}
