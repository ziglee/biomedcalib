package net.cassiolandim.biomedcalib.web.page.user;

import java.util.List;

import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.persistence.MockListPersistenceService;
import net.cassiolandim.biomedcalib.persistence.UserFixture;
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
public class TestUserListPage {
	
	private WicketTester tester;
	private LaboratoryFixture laboratoryFixture;
	private UserFixture userFixture;

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
		tester.clickLink("userListLink");
	}

	@Test
	public void shouldRenderLaboratoryListPage(){
		tester.assertRenderedPage(UserListPage.class);
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
	public void clickNewLinkShouldRenderUserEditPage() {
		tester.clickLink("newLink");
		tester.assertRenderedPage(UserEditPage.class);
	}
	
	@Test
	public void containsUsersList(){
		tester.assertComponent("users", ListView.class);
	}
	
	@Test
	public void usersListViewContainsNameLabel() {
		tester.assertComponent("users:1:name", Label.class);
	}
	
	@Test
	public void usersListViewContainsEditLink() {
		tester.assertComponent("users:1:editLink", Link.class);
	}
	
	@Test
	public void usersListViewContainsDeleteLink() {
		tester.assertComponent("users:1:deleteLink", Link.class);
	}
	
	@Test
	public void clickEditLinkFromUsersListViewShouldRenderUserEditPage() {
		tester.clickLink("users:1:editLink");
		tester.assertRenderedPage(UserEditPage.class);
	}
	
	@Test
	public void clickDeleteLinkFromLaboratoriesListViewShouldDeleteAndFeedbackInfoMessage() {
		UserListPage page = (UserListPage)tester.getLastRenderedPage();
		ListView<User> users = (ListView<User>)page.get("users");
		
		Assert.assertEquals(4, users.size());
		Assert.assertFalse(userFixture.getUserData().isUserDaoDeleteCalled());
		
		tester.clickLink("users:1:deleteLink");
		tester.assertRenderedPage(UserListPage.class);
		tester.assertInfoMessages(new String[]{"Usuário excluído com sucesso!"});
		
		Assert.assertEquals(3, users.size());
		Assert.assertTrue(userFixture.getUserData().isUserDaoDeleteCalled());
	}
}
