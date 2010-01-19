package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.component.HomeLink;
import net.cassiolandim.biomedcalib.web.BiomedcalibSession;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author Cassio Landim
 */
public class BasePage extends WebPage {

	public BiomedcalibSession getBiomedicalSession(){
		return (BiomedcalibSession) getSession();
	}
	
	protected void addHomeLink(){
		add(new HomeLink());
	}
}
