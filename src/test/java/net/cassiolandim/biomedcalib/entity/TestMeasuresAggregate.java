package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestMeasuresAggregate {

	private MeasuresAggregate aggregate = new MeasuresAggregate();
	private Measure m1 = new Measure();
	private Measure m2 = new Measure();
	
	@Before
	public void setUp(){
		m1.value = new Long(10);
		m2.value = new Long(20);
		
		aggregate.addMeasure(m1);
		aggregate.addMeasure(m2);
	}
	
	@Test
	public void testMean(){
		Assert.assertEquals(15, aggregate.getMean(), 0.001);
	}
	
	@Test
	public void testStandardDeviation(){
		Assert.assertEquals(7.071, aggregate.getStandardDeviation(), 0.001);
	}
	
	@Test
	public void testCofficientOfVariation(){
		Assert.assertEquals(47.140, aggregate.getCofficientOfVariation(), 0.001);
	}
}
