package org.capestart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Integer id;
	
	@Column(name="USER_NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="IS_ADMIN")
	private Character isAdmin;
	
	@Column(name="ACTIVE")
	private Character active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Character isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Character getActive() {
		return active;
	}

	public void setActive(Character active) {
		this.active = active;
	}
	
}
