package net.cassiolandim.biomedcalib.web.page;

import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;
import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListPage;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestAdminHomePage {
	
	private WicketTester tester;
	LaboratoryFixture laboratoryFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		laboratoryFixture = new LaboratoryFixture();
		laboratoryFixture.addStubs(app.context);
		
		tester.startPage(AdminHomePage.class);
	}

	@Test
	public void testRenderMyPage(){
		tester.assertRenderedPage(AdminHomePage.class);
	}
	
	@Test
	public void testGotoLaboratoryListingPage(){
		tester.assertComponent("labListLink", Link.class);
		tester.clickLink("labListLink");
		tester.assertRenderedPage(LaboratoryListPage.class);
	}
}
