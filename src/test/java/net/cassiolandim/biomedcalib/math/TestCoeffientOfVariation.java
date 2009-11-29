package net.cassiolandim.biomedcalib.math;

import net.cassiolandim.biomedcalib.math.CoeffientOfVariation;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Cassio Landim
 */
public class TestCoeffientOfVariation {

	@Test
	public void testCoeffientOfVariation(){
		CoeffientOfVariation cv = new CoeffientOfVariation(199.55, 4.19);
		Assert.assertEquals((4.19/199.55) * 100, cv.getResult(), 0.001);
	}
}
