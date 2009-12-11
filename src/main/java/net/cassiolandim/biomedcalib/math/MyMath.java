package net.cassiolandim.biomedcalib.math;

/**
 * Class with math functions
 * @author Cassio Landim
 */
public abstract class MyMath {
	
	private MyMath() {}
	
	/**
	 * Calculates de coefficient of variation of the
	 * standard deviantion and the mean
	 */
	public static double coeffientOfVariation(final double standartDeviation, final double mean) {
		return (standartDeviation/mean) * 100;
	}
}
