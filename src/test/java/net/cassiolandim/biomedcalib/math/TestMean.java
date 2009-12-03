package net.cassiolandim.biomedcalib.math;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.junit.Assert;
import org.junit.Test;

public class TestMean {

	@Test
	public void testMean(){
		Mean mean = new Mean();
		mean.increment(10);
		mean.increment(20);
		mean.increment(30);
		Assert.assertEquals((10+20+30) / 3, mean.getResult(), 0.001);
	}
}
