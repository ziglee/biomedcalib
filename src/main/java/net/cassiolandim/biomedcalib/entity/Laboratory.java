package net.cassiolandim.biomedcalib.entity;

import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author Cassio Landim
 */
@Entity
public class Laboratory extends BaseEntity<Laboratory> {

	private Long id;
	private String name;
	private byte[] logomark;
	private Integer doublePrecision;

	public Laboratory() {
		doublePrecision = new Integer(2);
	}
	
	@Id
	@Column(name="id_laboratory")
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
	
	@Lob
	@Column
	public byte[] getLogomark() {
		return logomark;
	}
	public void setLogomark(byte[] logomark) {
		this.logomark = logomark;
	}
	
	@Column
	public Integer getDoublePrecision() {
		return doublePrecision;
	}
	public void setDoublePrecision(Integer doublePrecision) {
		this.doublePrecision = doublePrecision;
	}

	public NumberFormat getNumberFormat(Locale locale){
		NumberFormat numberFormat = NumberFormat.getInstance(locale);
		numberFormat.setMaximumFractionDigits(getDoublePrecision());
		return numberFormat;
	}
	
	public int compareTo(Laboratory laboratory) {
		if(this.name != null && laboratory.name != null)
			return this.name.compareToIgnoreCase(laboratory.name);
		
		return 0;
	}
}
