package net.cassiolandim.biomedcalib.math;

/**
 * @author Cassio Landim
 */
public class CoeffientOfVariation {

	private double result;

	public CoeffientOfVariation(double mean, double standartDeviation) {
		this.result = (standartDeviation/mean) * 100;
	}

	public double getResult() {
		return result;
	}
}
