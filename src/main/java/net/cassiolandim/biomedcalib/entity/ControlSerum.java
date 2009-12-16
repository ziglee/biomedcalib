package net.cassiolandim.biomedcalib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.apache.commons.math.stat.descriptive.moment.Mean;

/**
 * @author Cassio Landim
 */
@Entity
public class ControlSerum extends BaseEntity<ControlSerum> {

	private Long id;
	private String name;
	private Double minimum;
	private Double maximum;
	private Double standardDeviation;
	private Double coefficientOfVariation;
	
	public final static double RANGE_MINIMUM = -200d;
	public final static double RANGE_MAXIMUM = 200d;

	@Id
	@Column(name="control_serum_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false,length=150)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(nullable=false)
	public Double getMinimum() {
		return minimum;
	}
	public void setMinimum(Double minimum) {
		this.minimum = minimum;
	}
	
	@Column(nullable=false)
	public Double getMaximum() {
		return maximum;
	}
	public void setMaximum(Double maximum) {
		this.maximum = maximum;
	}
	
	@Column(nullable=false)
	public Double getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(Double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	
	@Column(nullable=false)
	public Double getCoefficientOfVariation() {
		return coefficientOfVariation;
	}
	public void setCoefficientOfVariation(Double coefficientOfVariation) {
		this.coefficientOfVariation = coefficientOfVariation;
	}
	
	public int compareTo(ControlSerum controleSerum) {
		if(this.name != null && controleSerum.name != null)
			return this.name.compareToIgnoreCase(controleSerum.name);

		if(this.id != null && controleSerum.id != null)
			return this.id.compareTo(controleSerum.id);
		
		return 0;
	}

	@Transient
	public Double getMean() {
		if(minimum == null && maximum == null)
			return 0d;
		
		Mean mean = new Mean();
		mean.increment(minimum);
		mean.increment(maximum);
		return mean.getResult();
	}
}
