package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestMeasuresPerLevel {

	private MeasuresPerLevel measures = new MeasuresPerLevel();
	private Measure m01 = new Measure(112);
	private Measure m02 = new Measure(115);
	private Measure m03 = new Measure(103);
	private Measure m04 = new Measure(107);
	private Measure m05 = new Measure(116);
	private Measure m06 = new Measure(108);
	private Measure m07 = new Measure(115);
	private Measure m08 = new Measure(111);
	private Measure m09 = new Measure(106);
	private Measure m10 = new Measure(110);
	private Measure m11 = new Measure(102);
	private Measure m12 = new Measure(100);
	private Measure m13 = new Measure(104);
	private Measure m14 = new Measure(105);
	private Measure m15 = new Measure(107);
	private Measure m16 = new Measure(116);
	private Measure m17 = new Measure(117);
	private Measure m18 = new Measure(116);
	private Measure m19 = new Measure(105);
	private Measure m20 = new Measure(100);
	private Measure m21 = new Measure(94);
	private Measure m22 = new Measure(98);
	private Measure m23 = new Measure(92);
	private Measure m24 = new Measure(94);
	private Measure m25 = new Measure(96);
	
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
