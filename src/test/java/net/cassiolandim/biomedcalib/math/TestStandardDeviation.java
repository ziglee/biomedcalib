package net.cassiolandim.biomedcalib.math;

import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestStandardDeviation {
	
	@Test
	public void testStandardDeviation(){
		StandardDeviation sd = new StandardDeviation();
		sd.increment(10);
		sd.increment(20);
		sd.increment(30);
		Assert.assertEquals(10, sd.getResult(), 0.001);
	}
}
