package net.cassiolandim.biomedcalib.web.page.laboratory;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.sampleDataGeneration.LaboratoryDataGenerator;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;
import net.cassiolandim.biomedcalib.web.page.AdminHomePage;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestLaboratoryListPage {
	
	private WicketTester tester;
	private LaboratoryFixture laboratoryFixture;

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
	public void containsFeedbackPanel() {
		tester.assertComponent("feedback", FeedbackPanel.class);
	}
	
	@Test
	public void containsNewLink() {
		tester.assertComponent("newLink", Link.class);
	}
	
	@Test
	public void clickNewLinkShouldRenderLaboratoryEditPage() {
		tester.clickLink("newLink");
		tester.assertRenderedPage(LaboratoryEditPage.class);
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
	public void laboratoriesListViewNameLabelShouldBeTheFirstNameFromArray() {
		LaboratoryListPage page = (LaboratoryListPage)tester.getLastRenderedPage();
		Label nameLabel = (Label)page.get("laboratories:1:name");
		Assert.assertEquals(LaboratoryDataGenerator.NAMES[0], nameLabel.getDefaultModelObjectAsString());
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

		@SuppressWarnings("unchecked")
		ListView<Laboratory> laboratories = (ListView<Laboratory>)page.get("laboratories");
		
		Assert.assertEquals(6, laboratories.size());
		Assert.assertFalse(laboratoryFixture.getLaboratoryData().isLaboratoryDaoDeleteCalled());
		
		tester.clickLink("laboratories:1:deleteLink");
		tester.assertRenderedPage(LaboratoryListPage.class);
		tester.assertInfoMessages(new String[]{"Laboratório excluído com sucesso!"});
		
		Assert.assertEquals(5, laboratories.size());
		Assert.assertTrue(laboratoryFixture.getLaboratoryData().isLaboratoryDaoDeleteCalled());
	}
}
