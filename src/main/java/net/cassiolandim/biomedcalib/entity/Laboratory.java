package net.cassiolandim.biomedcalib.entity;

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
	
	public int compareTo(Laboratory laboratory) {
		if(this.name != null && laboratory.name != null)
			return this.name.compareToIgnoreCase(laboratory.name);
		
		return 0;
	}
}
