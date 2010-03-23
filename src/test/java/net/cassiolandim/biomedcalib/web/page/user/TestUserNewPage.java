package net.cassiolandim.biomedcalib.web.page.user;

import java.util.List;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.entity.User;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.persistence.MockListPersistenceService;
import net.cassiolandim.biomedcalib.persistence.UserFixture;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestUserNewPage {
	
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

		tester.startPage(UserListPage.class);
		tester.clickLink("newLink");
	}

	@Test
	public void shouldRenderUserEditPage(){
		tester.assertRenderedPage(UserEditPage.class);
	}
	
	@Test
	public void formNameTextFieldShouldBeEmpty(){
		UserEditPage page = (UserEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		TextField<String> nameTextField = (TextField<String>)page.get("form:name");
		
		Assert.assertEquals("", nameTextField.getDefaultModelObjectAsString());
	}
	
	@Test
	public void clickSaveButton() {
		FormTester formTester = tester.newFormTester("form");
		formTester.select("laboratory", 2);
		formTester.setValue("name", "Novo nome");
		formTester.setValue("login", "Login nome");
		formTester.setValue("password", "123456");
		formTester.select("active", 0);
		formTester.select("admin", 1);
		
		Assert.assertFalse(userFixture.getUserData().isUserDaoSaveCalled());
		
		formTester.submit("save");
		
		Assert.assertTrue(userFixture.getUserData().isUserDaoSaveCalled());
		
		tester.assertRenderedPage(UserEditPage.class);
		
		UserEditPage page = (UserEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		Form<User> form = (Form<User>)page.get("form");
		
		Assert.assertEquals("Novo nome", form.getModelObject().getName());
		Assert.assertEquals(LaboratoryFixture.NAMES[5], form.getModelObject().getLaboratory().getName());
		
		tester.assertInfoMessages(new String[]{"Usuário salvo com sucesso!"});
	}
}
