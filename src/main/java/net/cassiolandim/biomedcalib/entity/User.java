package net.cassiolandim.biomedcalib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 * @author Cassio Landim
 */
@Entity
public class User extends BaseEntity<User> {

	private Long id;
	private String name;
	private String login;
	private String passwordHash;
	private Laboratory laboratory;
	private Boolean admin;
	private Boolean active;
	private byte[] signature;
	
	@Id
	@Column(name="id_user")
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

	@Column(nullable=false,length=30)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Column(nullable=false)
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	@Column(nullable=false)
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	@Column(nullable=false)
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Lob
	@Column
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="id_laboratory")
	public Laboratory getLaboratory() {
		return laboratory;
	}
	public void setLaboratory(Laboratory laboratory) {
		this.laboratory = laboratory;
	}
	
	public int compareTo(User user) {
		if(this.name != null && user.name != null)
			return this.name.compareToIgnoreCase(user.name);
		
		return 0;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		if(id != null)
			sb.append("id: " + id.toString() + " ");
	
		if(name != null)
			sb.append("name: " + name + " ");
		
		if(laboratory != null)
			sb.append("lab: " + laboratory.getName());
		
		return sb.toString();
	}
}
