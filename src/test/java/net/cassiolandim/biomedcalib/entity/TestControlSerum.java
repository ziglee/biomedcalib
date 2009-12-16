package net.cassiolandim.biomedcalib.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestControlSerum {

	private ControlSerum m01 = new ControlSerum();
	private ControlSerum m02 = new ControlSerum();
	
	@Before
	public void setUp(){
		m01.setMinimum(100d);
		m01.setMaximum(200d);
	}
	
	@Test
	public void testMean(){
		Assert.assertEquals((100d + 200d) / 2, m01.getMean(), 0.001);
		Assert.assertEquals(0, m02.getMean(), 0.001);
	}
}
