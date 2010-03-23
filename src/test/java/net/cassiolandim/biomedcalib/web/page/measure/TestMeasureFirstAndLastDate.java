package net.cassiolandim.biomedcalib.web.page.measure;

import java.text.ParseException;

import net.cassiolandim.biomedcalib.entity.Measure;
import net.cassiolandim.biomedcalib.entity.MeasuresAggregate;
import net.cassiolandim.biomedcalib.sampleDataGeneration.MeasuresDataGenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMeasureFirstAndLastDate {

	private MeasuresAggregate ma;
	private final static String firstDate = "07/10/1981";
	private final static String lastDate = "06/09/1983";
	private Measure firstMeasure;
	private Measure lastMeasure;
	
	@Before
	public void setUp() throws ParseException{
		ma = new MeasuresAggregate();
		firstMeasure = MeasuresDataGenerator.createMeasure(ma, firstDate, 0);
		lastMeasure = MeasuresDataGenerator.createMeasure(ma, lastDate, 0);
		
		ma.addMeasure(firstMeasure);
		ma.addMeasure(MeasuresDataGenerator.createMeasure(ma, "20/01/1982", 0));
		ma.addMeasure(lastMeasure);
		
		ma.setFirstAndLastDate();
	}
	
	@Test
	public void assertFirstDate(){
		Assert.assertEquals(firstMeasure.getDate(), ma.getFirstDate());
	}
	
	@Test
	public void assertLastDate(){
		Assert.assertEquals(lastMeasure.getDate(), ma.getLastDate());
	}
}
