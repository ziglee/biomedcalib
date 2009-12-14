package net.cassiolandim.biomedcalib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Cassio Landim
 */
@Entity
public class Laboratory extends BaseEntity<Laboratory> {

	private Long id;
	private String name;

	@Id
	@Column(name="laboratory_id")
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
	
	public int compareTo(Laboratory laboratory) {
		if(this.name != null && laboratory.name != null)
			return this.name.compareToIgnoreCase(laboratory.name);
		
		if(this.id != null && laboratory.id != null)
			return this.id.compareTo(laboratory.id);
		
		return 0;
	}
}
