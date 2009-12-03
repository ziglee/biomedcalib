package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.BiomedcalibSession;

import org.apache.wicket.markup.html.WebPage;

public class BasePage extends WebPage {

	public BiomedcalibSession getBiomedicalSession(){
		return (BiomedcalibSession) getSession();
	}
}
