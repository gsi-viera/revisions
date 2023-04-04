package com.avangenio.ecasa_inv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Container {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private EContainer containerType;



	public Container() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Container(EContainer containerType) {
		super();
		this.containerType = containerType;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EContainer getContainerType() {
		return containerType;
	}

	public void setContainerType(EContainer containerType) {
		this.containerType = containerType;
	}

	
}
