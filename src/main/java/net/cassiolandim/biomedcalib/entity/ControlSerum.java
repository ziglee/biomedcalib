package net.cassiolandim.biomedcalib.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private Laboratory laboratory;
	private Integer status;
	
	public final static double RANGE_MINIMUM = -200d;
	public final static double RANGE_MAXIMUM = 200d;
	
	public final static int STATUS_PREP = 0;
	public final static int STATUS_ACTIVE = 1;
	public final static int STATUS_INACTIVE = 2;
	private final static Map<Integer, String> statusMap = new HashMap<Integer, String>();
	
	static{
		statusMap.put(STATUS_PREP, "Em preparo");
		statusMap.put(STATUS_ACTIVE, "Ativo");
		statusMap.put(STATUS_INACTIVE, "Inativo");
	}
	
	public ControlSerum() {
		setStatus(ControlSerum.STATUS_PREP);
	}
	
	public ControlSerum(Laboratory laboratory) {
		this();
		setLaboratory(laboratory);
	}
	
	@Id
	@Column(name="id_control_serum")
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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_laboratory")
	public Laboratory getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	
	@Column(nullable=false)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public int compareTo(ControlSerum controleSerum) {
		if(this.name != null && controleSerum.name != null)
			return this.name.compareToIgnoreCase(controleSerum.name);

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

	@Transient
	public String getStatusString(){
		return statusMap.get(getStatus());
	}

	@Transient
	public String getStatusString(int status){
		return statusMap.get(status);
	}
	
	@Transient
	public static Map<Integer, String> getStatusMap(){
		return new HashMap<Integer, String>(statusMap);
	}
}
