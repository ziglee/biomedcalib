package net.cassiolandim.biomedcalib.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import net.cassiolandim.biomedcalib.math.MyMath;

import org.apache.commons.math.stat.descriptive.moment.Mean;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

/**
 * @author Cassio Landim
 */
@Entity
public class MeasuresPerLevel extends BaseEntity<MeasuresPerLevel>{

	private Long id;
	private ControlSerum controlSerum;
	private List<Measure> measures;
	private MeasuresAggregate measuresAggregate;
	
	private MeasuresPerLevel() {
		setMeasures(new ArrayList<Measure>());
	}
	
	public MeasuresPerLevel(MeasuresAggregate measuresAggregate) {
		this();
		setMeasuresAggregate(measuresAggregate);
	}
	
	@Id
	@Column(name="id_measures_per_level")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_control_serum")
	public ControlSerum getControlSerum() {
		return controlSerum;
	}
	public void setControlSerum(ControlSerum controlSerum) {
		this.controlSerum = controlSerum;
	}
	
	@OneToMany
	public List<Measure> getMeasures() {
		return measures;
	}
	private void setMeasures(List<Measure> measures) {
		this.measures = measures;
	}
	public void addMeasure(Measure measure) {
		measures.add(measure);
	}

	@OneToOne
	public MeasuresAggregate getMeasuresAggregate() {
		return measuresAggregate;
	}
	private void setMeasuresAggregate(MeasuresAggregate measuresAggregate) {
		this.measuresAggregate = measuresAggregate;
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
	
	public int compareTo(MeasuresPerLevel mpl) {
		if(this.controlSerum != null && mpl.controlSerum != null)
			return this.controlSerum.compareTo(mpl.controlSerum);
		
		return 0;
	}
}
