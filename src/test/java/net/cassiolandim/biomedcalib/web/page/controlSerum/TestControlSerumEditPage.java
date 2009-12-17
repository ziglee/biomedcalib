package net.cassiolandim.biomedcalib.web.page.controlSerum;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.persistence.ControlSerumFixture;
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
public class TestControlSerumEditPage {
	
	private WicketTester tester;
	private ControlSerumFixture controlSerumFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		controlSerumFixture = new ControlSerumFixture();
		controlSerumFixture.addStubs(app.context);

		tester.startPage(ControlSerumListPage.class);
		tester.clickLink("controlSerums:1:editLink");
	}

	@Test
	public void shouldRenderControlSerumEditPage(){
		tester.assertRenderedPage(ControlSerumEditPage.class);
	}
	
	@Test
	public void containsListLink() {
		tester.assertComponent("listLink", Link.class);
	}
	
	@Test
	public void clickListLinkShouldRenderControlSerumListPage() {
		tester.clickLink("listLink");
		tester.assertRenderedPage(ControlSerumListPage.class);
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
		ControlSerumEditPage page = (ControlSerumEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		TextField<String> nameTextField = (TextField<String>)page.get("form:name");
		
		Assert.assertEquals(ControlSerumFixture.NAMES[1], nameTextField.getDefaultModelObjectAsString());
	}
	
	@Test
	public void formContainsSaveButton(){
		tester.assertComponent("form:save", Button.class);
	}
	
	@Test
	public void clickSaveButton() {
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "Circular");
		formTester.setValue("minimum", "90");
		formTester.setValue("maximum", "100");
		formTester.setValue("standardDeviation", "15");
		formTester.setValue("coefficientOfVariation", "50");
		
		Assert.assertFalse(controlSerumFixture.getControlSerumData().isControlSerumDaoSaveCalled());
		
		formTester.submit("save");
		
		Assert.assertTrue(controlSerumFixture.getControlSerumData().isControlSerumDaoSaveCalled());
		
		tester.assertRenderedPage(ControlSerumEditPage.class);
		tester.assertInfoMessages(new String[]{"Soro controle salvo com sucesso!"});
		
		ControlSerumEditPage page = (ControlSerumEditPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		Form<ControlSerum> form = (Form<ControlSerum>)page.get("form");
		
		Assert.assertEquals("Circular", form.getModelObject().getName());
	}
}