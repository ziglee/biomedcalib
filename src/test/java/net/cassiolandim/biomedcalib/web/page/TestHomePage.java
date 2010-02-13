package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestHomePage {
	
	private WicketTester tester;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		tester.startPage(HomePage.class);
	}

	@Test
	public void testRenderMyPage(){
		tester.assertRenderedPage(HomePage.class);
	}
	
	@Test
	public void containsAdminHomeLink() {
		tester.assertComponent("adminHomeLink", Link.class);
	}
	
	@Test
	public void clickAdminHomeLinkShouldRenderAdminHomePage(){
		tester.clickLink("adminHomeLink");
		tester.assertRenderedPage(AdminHomePage.class);
	}
}
