package net.cassiolandim.biomedcalib.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.bindgen.Bindable;

/**
 * @author Cassio Landim
 */
@Entity
@Bindable
public class Measure extends BaseEntity<Measure>{

	private Long id;
	private Date date;
	private Long value;
	private MeasuresPerLevel measuresPerLevel;
	
	private Measure() {
	}
	
	public Measure(MeasuresPerLevel measuresPerLevel) {
		this();
		setMeasuresPerLevel(measuresPerLevel);
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
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
	@ManyToOne
	public MeasuresPerLevel getMeasuresPerLevel() {
		return measuresPerLevel;
	}
	private void setMeasuresPerLevel(MeasuresPerLevel measuresPerLevel) {
		this.measuresPerLevel = measuresPerLevel;
	}
	
	public int compareTo(Measure measure) {
		if(this.date != null && measure.date != null)
			return this.date.compareTo(measure.date);
		
		return 0;
	}
}
