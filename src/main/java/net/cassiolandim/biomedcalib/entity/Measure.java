package net.cassiolandim.biomedcalib.entity;

/**
 * @author Cassio Landim
 */
public class Measure {

	private Long value;
	
	public Measure(long value) {
		this.value = value;
	}
	
	public Long getValue() {
		return value;
	}
}
