package net.cassiolandim.biomedcalib.entity;

import java.util.Date;

/**
 * @author Cassio Landim
 */
public class Measure {

	private Date date;
	private Long value;
	
	public Measure(long value) {
		this.value = value;
		this.date = new Date();
	}
	
	public Long getValue() {
		return value;
	}
	
	public Date getDate() {
		return date;
	}
}
