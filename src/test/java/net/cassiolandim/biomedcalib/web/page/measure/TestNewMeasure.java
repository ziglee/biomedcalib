package net.cassiolandim.biomedcalib.web.page.measure;

import net.cassiolandim.biomedcalib.web.BiomedcalibWicketTester;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

public class TestNewMeasure {

	private WicketTester tester;
	
	@Before
	public void setUp() throws Exception {
		tester = new BiomedcalibWicketTester();
		tester.startPage(NewMeasurePage.class);
	}
	
	@Test
	public void testRenderedPage(){
		tester.assertRenderedPage(NewMeasurePage.class);
	}
	
	@Test
	public void testForm(){
		tester.assertComponent("form", Form.class);
	}
	
	@Test
	public void testLaboratory(){
		tester.assertComponent("form:laboratory.name", Label.class);
	}
}
