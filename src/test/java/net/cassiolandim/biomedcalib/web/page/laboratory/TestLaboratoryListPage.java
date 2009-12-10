package net.cassiolandim.biomedcalib.web.page.laboratory;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;
import net.cassiolandim.biomedcalib.web.page.AdminHomePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestLaboratoryListPage {
	
	private WicketTester tester;
	LaboratoryFixture laboratoryFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		laboratoryFixture = new LaboratoryFixture();
		laboratoryFixture.addStubs(app.context);

		tester.startPage(AdminHomePage.class);
		tester.clickLink("labListLink");
	}

	@Test
	public void testRenderMyPage(){
		tester.assertRenderedPage(LaboratoryListPage.class);
	}
	
	@Test
	public void testAssertLaboratoriesList(){
		tester.assertComponent("laboratories", ListView.class);
	}
	
	@Test
	public void testLaboratoriesContainsNameLabel() {
		tester.assertComponent("laboratories:1:name", Label.class);
	}
	
	@Test
	public void testLaboratoriesContainsEditLink() {
		tester.assertComponent("laboratories:1:editLink", Link.class);
	}
	
	@Test
	public void testLaboratoriesContainsDeleteLink() {
		tester.assertComponent("laboratories:1:deleteLink", Link.class);
	}
	
	@Test
	public void testLaboratoriesClickEditLink() {
		tester.clickLink("laboratories:1:editLink");
		tester.assertRenderedPage(LaboratoryEditPage.class);
	}
	
	@Test
	public void testLaboratoriesClickDeleteLink() {
		LaboratoryListPage page = (LaboratoryListPage)tester.getLastRenderedPage();
		ListView<Laboratory> laboratories = (ListView<Laboratory>)page.get("laboratories");
		
		Assert.assertEquals(6, laboratories.size());
		
		tester.clickLink("laboratories:1:deleteLink");
		tester.assertRenderedPage(LaboratoryListPage.class);
		
		Assert.assertEquals(5, laboratories.size());
	}
}
