package net.cassiolandim.biomedcalib.web.page.laboratory;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.web.BiomedcalibApplicationForTesting;
import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;

import org.apache.wicket.markup.html.form.Button;
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
public class TestLaboratoryEditPage {
	
	private WicketTester tester;
	private LaboratoryFixture laboratoryFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		laboratoryFixture = new LaboratoryFixture();
		laboratoryFixture.addStubs(app.context);

		tester.startPage(LaboratoryListPage.class);
		tester.clickLink("laboratories:1:editLink");
	}

	@Test
	public void shouldRenderLaboratoryEditPage(){
		tester.assertRenderedPage(LaboratoryEditPage.class);
	}
	
	@Test
	public void containsListLink() {
		tester.assertComponent("listLink", Link.class);
	}
	
	@Test
	public void clickListLinkShouldRenderLaboratoryListPage() {
		tester.clickLink("listLink");
		tester.assertRenderedPage(LaboratoryListPage.class);
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
		LaboratoryEditPage page = (LaboratoryEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		TextField<String> nameTextField = (TextField<String>)page.get("form:name");
		
		Assert.assertEquals(LaboratoryFixture.NAMES[0], nameTextField.getDefaultModelObjectAsString());
	}
	
	@Test
	public void formContainsSaveButton(){
		tester.assertComponent("form:save", Button.class);
	}
	
	@Test
	public void clickSaveButton() {
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "Circular");
		
		Assert.assertFalse(laboratoryFixture.getLaboratoryData().isLaboratoryDaoSaveCalled());
		
		formTester.submit("save");
		
		Assert.assertTrue(laboratoryFixture.getLaboratoryData().isLaboratoryDaoSaveCalled());
		
		tester.assertRenderedPage(LaboratoryEditPage.class);
		tester.assertInfoMessages(new String[]{"Laboratório salvo com sucesso!"});
		
		LaboratoryEditPage page = (LaboratoryEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		Form<Laboratory> form = (Form<Laboratory>)page.get("form");
		
		Assert.assertEquals("Circular", form.getModelObject().getName());
	}
}
