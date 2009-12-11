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
	public void shouldRenderLaboratoryListPage(){
		tester.assertRenderedPage(LaboratoryListPage.class);
	}
	
	@Test
	public void containsNewLink() {
		tester.assertComponent("newLink", Link.class);
	}
	
	@Test
	public void clickNewLinkShouldRenderLaboratoryNewPage() {
		tester.clickLink("newLink");
		tester.assertRenderedPage(LaboratoryNewPage.class);
	}
	
	@Test
	public void containsLaboratoriesList(){
		tester.assertComponent("laboratories", ListView.class);
	}
	
	@Test
	public void laboratoriesListViewContainsNameLabel() {
		tester.assertComponent("laboratories:1:name", Label.class);
	}
	
	@Test
	public void laboratoriesListViewContainsEditLink() {
		tester.assertComponent("laboratories:1:editLink", Link.class);
	}
	
	@Test
	public void laboratoriesListViewContainsDeleteLink() {
		tester.assertComponent("laboratories:1:deleteLink", Link.class);
	}
	
	@Test
	public void clickEditLinkFromLaboratoriesListViewShouldRenderLaboratoryEditPage() {
		tester.clickLink("laboratories:1:editLink");
		tester.assertRenderedPage(LaboratoryEditPage.class);
	}
	
	@Test
	public void clickDeleteLinkFromLaboratoriesListViewShouldDeleteAndFeedbackInfoMessage() {
		LaboratoryListPage page = (LaboratoryListPage)tester.getLastRenderedPage();
		ListView<Laboratory> laboratories = (ListView<Laboratory>)page.get("laboratories");
		
		Assert.assertEquals(6, laboratories.size());
		
		tester.clickLink("laboratories:1:deleteLink");
		tester.assertRenderedPage(LaboratoryListPage.class);
		
		Assert.assertEquals(5, laboratories.size());
		
		tester.assertInfoMessages(new String[]{"Laboratório excluído com sucesso!"});
	}
}
