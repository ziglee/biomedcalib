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

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestUserEditPage {
	
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
		tester.clickLink("users:1:editLink");
	}

	@Test
	public void shouldRenderUserEditPage(){
		tester.assertRenderedPage(UserEditPage.class);
	}
	
	@Test
	public void containsListLink() {
		tester.assertComponent("listLink", Link.class);
	}
	
	@Test
	public void clickListLinkShouldRenderUserListPage() {
		tester.clickLink("listLink");
		tester.assertRenderedPage(UserListPage.class);
	}
	
	@Test
	public void containsForm(){
		tester.assertComponent("form", Form.class);
	}
	
	@Test
	public void formContainsNameTextField(){
		tester.assertComponent("form:name", TextField.class);
	}
	
	@Test
	public void formNameTextFieldShouldContainFirstNameFromDataArray(){
		UserEditPage page = (UserEditPage)tester.getLastRenderedPage();
		
		@SuppressWarnings("unchecked")
		TextField<String> nameTextField = (TextField<String>)page.get("form:name");
		
		Assert.assertEquals(UserFixture.NAMES[1], nameTextField.getDefaultModelObjectAsString());
	}
	
	@Test
	public void formContainsLaboratoryDropDownChoice(){
		tester.assertComponent("form:laboratory", DropDownChoice.class);
	}
	
	@Test
	public void formLaboratoryDropDownChoiceShouldContainFirstLaboratoryFromDataArray(){
		UserEditPage page = (UserEditPage)tester.getLastRenderedPage();
		
		@SuppressWarnings("unchecked")
		DropDownChoice<Laboratory> laboratoryDropDownChoice = (DropDownChoice<Laboratory>)page.get("form:laboratory");

		Assert.assertEquals(LaboratoryFixture.NAMES[0], laboratoryDropDownChoice.getModelObject().getName());
	}
	
	@Test
	public void formContainsSaveButton(){
		tester.assertComponent("form:save", Button.class);
	}
	
	@Test
	public void clickSaveButton() {
		FormTester formTester = tester.newFormTester("form");
		formTester.select("laboratory", 2);
		formTester.setValue("name", "Nome alterado");
		
		Assert.assertFalse(userFixture.getUserData().isUserDaoSaveCalled());
		
		formTester.submit("save");
		
		Assert.assertTrue(userFixture.getUserData().isUserDaoSaveCalled());
		
		tester.assertRenderedPage(UserEditPage.class);
		tester.assertInfoMessages(new String[]{"Usuário salvo com sucesso!"});
		
		UserEditPage page = (UserEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		Form<User> form = (Form<User>)page.get("form");
		
		Assert.assertEquals("Nome alterado", form.getModelObject().getName());
		Assert.assertEquals(LaboratoryFixture.NAMES[5], form.getModelObject().getLaboratory().getName());
	}
}
