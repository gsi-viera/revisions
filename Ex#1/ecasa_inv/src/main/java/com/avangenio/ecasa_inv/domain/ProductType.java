package com.avangenio.ecasa_inv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductType {
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private EProductType productType;

	
	
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductType(EProductType productType) {
		super();
		this.productType = productType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EProductType getProductType() {
		return productType;
	}

	public void setProductType(EProductType productType) {
		this.productType = productType;
	}

	

}
