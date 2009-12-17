package net.cassiolandim.biomedcalib.web.page.controlSerum;

import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.persistence.ControlSerumFixture;
import net.cassiolandim.biomedcalib.sampleDataGeneration.ControlSerumDataGenerator;
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
public class TestControlSerumListPage {
	
	private WicketTester tester;
	private ControlSerumFixture controlSerumFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		controlSerumFixture = new ControlSerumFixture();
		controlSerumFixture.addStubs(app.context);

		tester.startPage(AdminHomePage.class);
		tester.clickLink("controlSerumListLink");
	}

	@Test
	public void shouldRenderControlSerumListPage(){
		tester.assertRenderedPage(ControlSerumListPage.class);
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
	public void clickNewLinkShouldRenderControlSerumEditPage() {
		tester.clickLink("newLink");
		tester.assertRenderedPage(ControlSerumEditPage.class);
	}
	
	@Test
	public void containsLaboratoriesList(){
		tester.assertComponent("controlSerums", ListView.class);
	}
	
	@Test
	public void controlSerumsListViewContainsNameLabel() {
		tester.assertComponent("controlSerums:1:name", Label.class);
	}
	
	@Test
	public void controlSerumsListViewNameLabelShouldBeTheFirstNameFromArray() {
		ControlSerumListPage page = (ControlSerumListPage)tester.getLastRenderedPage();
		Label nameLabel = (Label)page.get("controlSerums:1:name");
		Assert.assertEquals(ControlSerumDataGenerator.NAMES[1], nameLabel.getDefaultModelObjectAsString());
	}
	
	@Test
	public void controlSerumsListViewContainsEditLink() {
		tester.assertComponent("controlSerums:1:editLink", Link.class);
	}
	
	@Test
	public void controlSerumsListViewContainsDeleteLink() {
		tester.assertComponent("controlSerums:1:deleteLink", Link.class);
	}
	
	@Test
	public void clickEditLinkFromLaboratoriesListViewShouldRenderControlSerumEditPage() {
		tester.clickLink("controlSerums:1:editLink");
		tester.assertRenderedPage(ControlSerumEditPage.class);
	}
	
	@Test
	public void clickDeleteLinkFromLaboratoriesListViewShouldDeleteAndFeedbackInfoMessage() {
		ControlSerumListPage page = (ControlSerumListPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		ListView<ControlSerum> controlSerums = (ListView<ControlSerum>)page.get("controlSerums");
		
		Assert.assertEquals(4, controlSerums.size());
		Assert.assertFalse(controlSerumFixture.getControlSerumData().isControlSerumDaoDeleteCalled());
		
		tester.clickLink("controlSerums:1:deleteLink");
		tester.assertRenderedPage(ControlSerumListPage.class);
		tester.assertInfoMessages(new String[]{"Soro controle exclu�do com sucesso!"});
		
		Assert.assertEquals(3, controlSerums.size());
		Assert.assertTrue(controlSerumFixture.getControlSerumData().isControlSerumDaoDeleteCalled());
	}
}