package net.cassiolandim.biomedcalib.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestCoeffientOfVariation {

	@Test
	public void testCoeffientOfVariation(){
		double result = MyMath.coeffientOfVariation(4.19, 199.55);
		Assert.assertEquals((4.19/199.55) * 100, result, 0.001);
	}
}
