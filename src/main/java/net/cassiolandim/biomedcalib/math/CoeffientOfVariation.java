package net.cassiolandim.biomedcalib.math;

/**
 * @author Cassio Landim
 */
public class CoeffientOfVariation {

	private double mean;
	private double standartDeviation;

	public CoeffientOfVariation(double mean, double standartDeviation) {
		super();
		this.mean = mean;
		this.standartDeviation = standartDeviation;
	}

	public double getResult() {
		return (standartDeviation/mean) * 100;
	}
}
