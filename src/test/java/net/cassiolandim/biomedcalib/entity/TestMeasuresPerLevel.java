package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestMeasuresPerLevel {

	private MeasuresAggregate measuresAggregate = new MeasuresAggregate(new Laboratory());
	private MeasuresPerLevel measures = new MeasuresPerLevel(measuresAggregate);
	private Measure m01 = new Measure(measures);
	private Measure m02 = new Measure(measures);
	private Measure m03 = new Measure(measures);
	private Measure m04 = new Measure(measures);
	private Measure m05 = new Measure(measures);
	private Measure m06 = new Measure(measures);
	private Measure m07 = new Measure(measures);
	private Measure m08 = new Measure(measures);
	private Measure m09 = new Measure(measures);
	private Measure m10 = new Measure(measures);
	private Measure m11 = new Measure(measures);
	private Measure m12 = new Measure(measures);
	private Measure m13 = new Measure(measures);
	private Measure m14 = new Measure(measures);
	private Measure m15 = new Measure(measures);
	private Measure m16 = new Measure(measures);
	private Measure m17 = new Measure(measures);
	private Measure m18 = new Measure(measures);
	private Measure m19 = new Measure(measures);
	private Measure m20 = new Measure(measures);
	private Measure m21 = new Measure(measures);
	private Measure m22 = new Measure(measures);
	private Measure m23 = new Measure(measures);
	private Measure m24 = new Measure(measures);
	private Measure m25 = new Measure(measures);
	
	@Before
	public void setUp(){
		measures.addMeasure(m01);
		measures.addMeasure(m02);
		measures.addMeasure(m03);
		measures.addMeasure(m04);
		measures.addMeasure(m05);
		measures.addMeasure(m06);
		measures.addMeasure(m07);
		measures.addMeasure(m08);
		measures.addMeasure(m09);
		measures.addMeasure(m10);
		measures.addMeasure(m11);
		measures.addMeasure(m12);
		measures.addMeasure(m13);
		measures.addMeasure(m14);
		measures.addMeasure(m15);
		measures.addMeasure(m16);
		measures.addMeasure(m17);
		measures.addMeasure(m18);
		measures.addMeasure(m19);
		measures.addMeasure(m20);
		measures.addMeasure(m21);
		measures.addMeasure(m22);
		measures.addMeasure(m23);
		measures.addMeasure(m24);
		measures.addMeasure(m25);
		
		m01.setValue(112L);
		m02.setValue(115L);
		m03.setValue(103L);
		m04.setValue(107L);
		m05.setValue(116L);
		m06.setValue(108L);
		m07.setValue(115L);
		m08.setValue(111L);
		m09.setValue(106L);
		m10.setValue(110L);
		m11.setValue(102L);
		m12.setValue(100L);
		m13.setValue(104L);
		m14.setValue(105L);
		m15.setValue(107L);
		m16.setValue(116L);
		m17.setValue(117L);
		m18.setValue(116L);
		m19.setValue(105L);
		m20.setValue(100L);
		m21.setValue(94L);
		m22.setValue(98L);
		m23.setValue(92L);
		m24.setValue(94L);
		m25.setValue(96L);
	}
	
	@Test
	public void testMean(){
		Assert.assertEquals(105.959, measures.getMean(), 0.001);
	}
	
	@Test
	public void testStandardDeviation(){
		Assert.assertEquals(7.683, measures.getStandardDeviation(), 0.001);
	}
	
	@Test
	public void testCofficientOfVariation(){
		Assert.assertEquals(7.251, measures.getCofficientOfVariation(), 0.001);
	}
}
