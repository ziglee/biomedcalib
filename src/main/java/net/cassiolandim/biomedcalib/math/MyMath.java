package net.cassiolandim.biomedcalib.math;

/**
 * @author Cassio Landim
 */
public abstract class MyMath {
	
	private MyMath() {}
	
	public static double coeffientOfVariation(final double standartDeviation, final double mean) {
		return (standartDeviation/mean) * 100;
	}
}
