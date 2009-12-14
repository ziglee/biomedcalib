package net.cassiolandim.biomedcalib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Cassio Landim
 */
@Entity
public class User extends BaseEntity implements Comparable<User> {

	private Long id;
	private String name;
	private Laboratory laboratory;
	
	@Id
	@Column(name="user_id")
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
	
	@ManyToOne(optional=false)
	@JoinColumn(name="laboratory_id")
	public Laboratory getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	
	public int compareTo(User user) {
		if(this.name!=null && user.name!=null)
			return this.name.compareToIgnoreCase(user.name);
		return 0;
	}
	
}
