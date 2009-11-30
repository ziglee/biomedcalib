package net.cassiolandim.biomedcalib.math;

public class MyMath {
	
	public static double coeffientOfVariation(double standartDeviation, double mean) {
		return (standartDeviation/mean) * 100;
	}
}
