package net.cassiolandim.biomedcalib.entity;

import java.io.Serializable;

/**
 * @author Cassio Landim
 */
public class MeasuresAggregate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Laboratory laboratory;
	public MeasuresPerLevel measures1;
	public MeasuresPerLevel measures2;
	public MeasuresPerLevel measures3;
	
	public MeasuresAggregate(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	
	public Laboratory getLaboratory() {
		return laboratory;
	}
}
