package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.web.BiomedcalibApplication;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 * 
 * @author Cassio Landim
 */
public class TestHomePage {
	private WicketTester tester;

	@Before
	public void setUp(){
		tester = new WicketTester(new BiomedcalibApplication());
	}

	@Test
	public void testRenderMyPage(){
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);

		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");
	}
}
