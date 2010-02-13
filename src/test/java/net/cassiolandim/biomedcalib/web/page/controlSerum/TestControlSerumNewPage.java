package net.cassiolandim.biomedcalib.web.page.controlSerum;

import java.util.List;

import junit.framework.Assert;
import net.cassiolandim.biomedcalib.entity.ControlSerum;
import net.cassiolandim.biomedcalib.entity.Laboratory;
import net.cassiolandim.biomedcalib.persistence.ControlSerumFixture;
import net.cassiolandim.biomedcalib.persistence.LaboratoryFixture;
import net.cassiolandim.biomedcalib.persistence.MockListPersistenceService;
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
public class TestControlSerumNewPage {
	
	private WicketTester tester;
	private ControlSerumFixture controlSerumFixture;
	private LaboratoryFixture laboratoryFixture;

	@Before
	public void setUp(){
		tester = new BiomedcalibWicketTester();
		
		BiomedcalibApplicationForTesting app = (BiomedcalibApplicationForTesting)tester.getApplication();
		
		laboratoryFixture = new LaboratoryFixture();
		laboratoryFixture.addStubs(app.context);
		
		MockListPersistenceService<Laboratory> laboratorySimplePersistableService = laboratoryFixture.getLaboratoryData().getLaboratoryService();
		List<Laboratory> labs = laboratorySimplePersistableService.findAll();
		
		controlSerumFixture = new ControlSerumFixture();
		controlSerumFixture.addStubs(app.context, labs);

		tester.startPage(ControlSerumListAdminPage.class);
		tester.clickLink("newLink");
	}

	@Test
	public void shouldRenderControlSerumEditPage(){
		tester.assertRenderedPage(ControlSerumEditAdminPage.class);
	}
	
	@Test
	public void formNameTextFieldShouldBeEmpty(){
		ControlSerumEditAdminPage page = (ControlSerumEditAdminPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		TextField<String> nameTextField = (TextField<String>)page.get("form:name");
		
		Assert.assertEquals("", nameTextField.getDefaultModelObjectAsString());
	}
	
	@Test
	public void clickSaveButton() {
		FormTester formTester = tester.newFormTester("form");
		formTester.setValue("name", "PDCA");
		formTester.setValue("minimum", "90");
		formTester.setValue("maximum", "100");
		formTester.setValue("standardDeviation", "15");
		formTester.setValue("coefficientOfVariation", "50");
		formTester.select("laboratory", 2);
		
		Assert.assertFalse(controlSerumFixture.getControlSerumData().isControlSerumDaoSaveCalled());
		
		formTester.submit("save");
		
		Assert.assertTrue(controlSerumFixture.getControlSerumData().isControlSerumDaoSaveCalled());
		
		tester.assertRenderedPage(ControlSerumEditAdminPage.class);
		
		ControlSerumEditAdminPage page = (ControlSerumEditAdminPage)tester.getLastRenderedPage();

		@SuppressWarnings("unchecked")
		Form<ControlSerum> form = (Form<ControlSerum>)page.get("form");
		
		Assert.assertEquals("PDCA", form.getModelObject().getName());
		
		tester.assertInfoMessages(new String[]{"Soro controle salvo com sucesso!"});
	}
}
