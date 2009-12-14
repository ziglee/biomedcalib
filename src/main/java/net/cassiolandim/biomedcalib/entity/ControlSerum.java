package net.cassiolandim.biomedcalib.entity;

/**
 * @author Cassio Landim
 */
public class ControlSerum {

	private String name;
	private Double minimum;
	private Double maximum;
	private Double mean;
	private Double standardDeviation;
	private Double coefficientOfVariation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMinimum() {
		return minimum;
	}
	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}
	public Double getMaximum() {
		return maximum;
	}
	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}
	public Double getMean() {
		return mean;
	}
	public void setMean(Double mean) {
		this.mean = mean;
	}
	public Double getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(Double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public Double getCoefficientOfVariation() {
		return coefficientOfVariation;
	}
	public void setCoefficientOfVariation(Double coefficientOfVariation) {
		this.coefficientOfVariation = coefficientOfVariation;
	}
}
