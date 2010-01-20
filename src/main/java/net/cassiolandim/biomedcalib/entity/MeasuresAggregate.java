package net.cassiolandim.biomedcalib.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.bindgen.Bindable;

/**
 * @author Cassio Landim
 */
@Entity
@Bindable
public class MeasuresAggregate extends BaseEntity<MeasuresAggregate>{

	private Long id;
	private Laboratory laboratory;
	private MeasuresPerLevel measures1;
	private MeasuresPerLevel measures2;
	private MeasuresPerLevel measures3;
	private String observation;
	private Date creationDate;
	
	private MeasuresAggregate() {
		setMeasures1(new MeasuresPerLevel(this));
		setMeasures2(new MeasuresPerLevel(this));
		setMeasures3(new MeasuresPerLevel(this));
		setCreationDate(new Date());
	}
	
	public MeasuresAggregate(Laboratory laboratory) {
		this();
		setLaboratory(laboratory);
	}
	
	@Id
	@Column(name="id_measures_aggregate")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_laboratory")
	public Laboratory getLaboratory() {
		return laboratory;
	}
	private void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}

	@OneToOne
	@JoinColumn(name = "id_measures_per_level_1", 
			insertable = true, 
			updatable = true) 
	public MeasuresPerLevel getMeasures1() {
		return measures1;
	}
	private void setMeasures1(MeasuresPerLevel measures1) {
		this.measures1 = measures1;
	}

	@OneToOne
	@JoinColumn(name = "id_measures_per_level_2", 
			insertable = true, 
			updatable = true) 
	public MeasuresPerLevel getMeasures2() {
		return measures2;
	}
	private void setMeasures2(MeasuresPerLevel measures2) {
		this.measures2 = measures2;
	}

	@OneToOne
	@JoinColumn(name = "id_measures_per_level_3", 
			insertable = true, 
			updatable = true) 
	public MeasuresPerLevel getMeasures3() {
		return measures3;
	}
	private void setMeasures3(MeasuresPerLevel measures3) {
		this.measures3 = measures3;
	}

	@Column(nullable=true,length=1500)
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	@Column(nullable=false,updatable=true)
	public Date getCreationDate() {
		return creationDate;
	}
	private void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int compareTo(MeasuresAggregate ma) {
		int comparison = 0;
		
		if(this.laboratory != null && ma.laboratory != null)
			comparison = this.laboratory.compareTo(ma.laboratory);
		
		if(comparison == 0)
			comparison = this.creationDate.compareTo(ma.creationDate);
		
		return comparison;
	}
}
