package net.cassiolandim.biomedcalib;

import net.cassiolandim.biomedcalib.web.WicketApplication;
import net.cassiolandim.biomedcalib.web.page.HomePage;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 * 
 * @author Cássio Landim
 */
public class TestHomePage {
	private WicketTester tester;

	@Before
	public void setUp(){
		tester = new WicketTester(new WicketApplication());
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
