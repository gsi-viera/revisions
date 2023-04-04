package com.avangenio.ecasa_inv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SecurityRole {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public SecurityRole( String role) {
		super();
		this.role = role;
	}
	
	public SecurityRole( int id, String role) {
		super();
		this.role = role;
		this.id = id;
	}

	public SecurityRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
