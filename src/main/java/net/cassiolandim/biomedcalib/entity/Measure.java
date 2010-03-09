package net.cassiolandim.biomedcalib.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Cassio Landim
 */
@Entity
public class Measure extends BaseEntity<Measure>{

	private Long id;
	private Date date;
	private Double value;
	private MeasuresAggregate measureAggregate;
	
	private Measure() {
		value = new Double(0);
		date = new Date();
	}
	
	public Measure(MeasuresAggregate measureAggregate) {
		this();
		setMeasureAggregate(measureAggregate);
	}
	
	@Id
	@Column(name="id_measure")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(nullable=false)
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	@ManyToOne
	public MeasuresAggregate getMeasureAggregate() {
		return measureAggregate;
	}
	public void setMeasureAggregate(MeasuresAggregate measureAggregate) {
		this.measureAggregate = measureAggregate;
	}
	
	public int compareTo(Measure measure) {
		if(this.date != null && measure.date != null)
			return this.date.compareTo(measure.date);
		
		return 0;
	}
}
