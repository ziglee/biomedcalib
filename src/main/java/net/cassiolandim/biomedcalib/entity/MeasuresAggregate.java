package net.cassiolandim.biomedcalib.entity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import net.cassiolandim.biomedcalib.math.MyMath;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

/**
 * @author Cassio Landim
 */
@Entity
public class MeasuresAggregate extends BaseEntity<MeasuresAggregate>{

	private Long id;
	private Laboratory laboratory;
	private ControlSerum controlSerum;
	private String observation;
	private Date firstDate;
	private Date lastDate;
	private List<Measure> measures;
	private Boolean active;

	public MeasuresAggregate() {
		setMeasures(new ArrayList<Measure>());
		active = Boolean.TRUE;
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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_control_serum")
	public ControlSerum getControlSerum() {
		return controlSerum;
	}
	public void setControlSerum(ControlSerum controlSerum) {
		this.controlSerum = controlSerum;
	}

	@Column(nullable=true,length=1500)
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}

	@Column
	public Date getFirstDate() {
		return firstDate;
	}
	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}
	
	@Column(nullable=false)
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@OneToMany(cascade = {CascadeType.REMOVE})
	public List<Measure> getMeasures() {
		return measures;
	}
	private void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}
	public void addMeasure(Measure measure) {
		measures.add(measure);
	}
	public void removeMeasure(Measure measure) {
		measures.remove(measure);
	}
	
	public int compareTo(MeasuresAggregate ma) {
		int comparison = this.laboratory.compareTo(ma.laboratory);
		
		if(comparison == 0)
			comparison = this.firstDate.compareTo(ma.firstDate);

		if(comparison == 0)
			comparison = this.controlSerum.compareTo(ma.controlSerum);
		
		return comparison;
	}

	@Transient
	public double getMean() {
		Mean mean = new Mean();
		
		for(Measure measure : measures)
			mean.increment(measure.getValue());
		
		return mean.getResult();
	}
	
	@Transient
	public double getStandardDeviation(){
		StandardDeviation sd = new StandardDeviation();
		
		for(Measure measure : measures)
			sd.increment(measure.getValue());
		
		return sd.getResult();
	}

	@Transient
	public double getCofficientOfVariation() {
		return MyMath.coeffientOfVariation(getStandardDeviation(), getMean());
	}
	
	@Transient
	public void setFirstAndLastDate(){
		if(measures != null){
			int size = measures.size();
			if(size > 0){
				setFirstDate(measures.get(0).getDate());
				setLastDate(measures.get(size - 1).getDate());
			}
		}
	}
}
