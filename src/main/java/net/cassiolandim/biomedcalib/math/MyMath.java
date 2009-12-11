package net.cassiolandim.biomedcalib.math;

/**
 * @author Cassio Landim
 */
public abstract class MyMath {
	
	public static double coeffientOfVariation(double standartDeviation, double mean) {
		return (standartDeviation/mean) * 100;
	}
}
