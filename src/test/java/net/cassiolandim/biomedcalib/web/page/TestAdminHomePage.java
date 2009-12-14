package net.cassiolandim.biomedcalib.web.page;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.persistence.MockListPersistenceService;
import net.cassiolandim.biomedcalib.persistence.UserFixture;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;
import net.cassiolandim.biomedcalib.web.page.laboratory.LaboratoryListPage;
import net.cassiolandim.biomedcalib.web.page.user.UserListPage;

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
	UserFixture userFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		laboratoryFixture = new LaboratoryFixture();
		laboratoryFixture.addStubs(app.context);
		
		MockListPersistenceService<Laboratory> laboratorySimplePersistableService = laboratoryFixture.getLaboratoryData().getLaboratoryService();
		List<Laboratory> labs = laboratorySimplePersistableService.findAll();
		
		userFixture = new UserFixture();
		userFixture.addStubs(app.context, labs);
		
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
	
	@Test
	public void testGotoUserListingPage(){
		tester.assertComponent("userListLink", Link.class);
		tester.clickLink("userListLink");
		tester.assertRenderedPage(UserListPage.class);
	}
}
