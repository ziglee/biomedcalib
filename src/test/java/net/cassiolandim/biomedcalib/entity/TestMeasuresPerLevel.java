package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestMeasuresPerLevel {

	private MeasuresAggregate measuresAggregate = new MeasuresAggregate(new Laboratory());
	private Measure m01 = new Measure(measuresAggregate);
	private Measure m02 = new Measure(measuresAggregate);
	private Measure m03 = new Measure(measuresAggregate);
	private Measure m04 = new Measure(measuresAggregate);
	private Measure m05 = new Measure(measuresAggregate);
	private Measure m06 = new Measure(measuresAggregate);
	private Measure m07 = new Measure(measuresAggregate);
	private Measure m08 = new Measure(measuresAggregate);
	private Measure m09 = new Measure(measuresAggregate);
	private Measure m10 = new Measure(measuresAggregate);
	private Measure m11 = new Measure(measuresAggregate);
	private Measure m12 = new Measure(measuresAggregate);
	private Measure m13 = new Measure(measuresAggregate);
	private Measure m14 = new Measure(measuresAggregate);
	private Measure m15 = new Measure(measuresAggregate);
	private Measure m16 = new Measure(measuresAggregate);
	private Measure m17 = new Measure(measuresAggregate);
	private Measure m18 = new Measure(measuresAggregate);
	private Measure m19 = new Measure(measuresAggregate);
	private Measure m20 = new Measure(measuresAggregate);
	private Measure m21 = new Measure(measuresAggregate);
	private Measure m22 = new Measure(measuresAggregate);
	private Measure m23 = new Measure(measuresAggregate);
	private Measure m24 = new Measure(measuresAggregate);
	private Measure m25 = new Measure(measuresAggregate);
	
	@Before
	public void setUp(){
		measuresAggregate.addMeasure(m01);
		measuresAggregate.addMeasure(m02);
		measuresAggregate.addMeasure(m03);
		measuresAggregate.addMeasure(m04);
		measuresAggregate.addMeasure(m05);
		measuresAggregate.addMeasure(m06);
		measuresAggregate.addMeasure(m07);
		measuresAggregate.addMeasure(m08);
		measuresAggregate.addMeasure(m09);
		measuresAggregate.addMeasure(m10);
		measuresAggregate.addMeasure(m11);
		measuresAggregate.addMeasure(m12);
		measuresAggregate.addMeasure(m13);
		measuresAggregate.addMeasure(m14);
		measuresAggregate.addMeasure(m15);
		measuresAggregate.addMeasure(m16);
		measuresAggregate.addMeasure(m17);
		measuresAggregate.addMeasure(m18);
		measuresAggregate.addMeasure(m19);
		measuresAggregate.addMeasure(m20);
		measuresAggregate.addMeasure(m21);
		measuresAggregate.addMeasure(m22);
		measuresAggregate.addMeasure(m23);
		measuresAggregate.addMeasure(m24);
		measuresAggregate.addMeasure(m25);
		
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
		Assert.assertEquals(105.959, measuresAggregate.getMean(), 0.001);
	}
	
	@Test
	public void testStandardDeviation(){
		Assert.assertEquals(7.683, measuresAggregate.getStandardDeviation(), 0.001);
	}
	
	@Test
	public void testCofficientOfVariation(){
		Assert.assertEquals(7.251, measuresAggregate.getCofficientOfVariation(), 0.001);
	}
}
