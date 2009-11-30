package net.cassiolandim.biomedcalib.entity;

/**
 * @author Cassio Landim
 */
public class MeasuresAggregate {

	private MeasuresPerLevel measures1;
	private MeasuresPerLevel measures2;
	private MeasuresPerLevel measures3;
	
	public MeasuresPerLevel getMeasures1() {
		return measures1;
	}
	public void setMeasures1(MeasuresPerLevel measures1) {
		this.measures1 = measures1;
	}
	
	public MeasuresPerLevel getMeasures2() {
		return measures2;
	}
	public void setMeasures2(MeasuresPerLevel measures2) {
		this.measures2 = measures2;
	}
	
	public MeasuresPerLevel getMeasures3() {
		return measures3;
	}
	public void setMeasures3(MeasuresPerLevel measures3) {
		this.measures3 = measures3;
	}
}
