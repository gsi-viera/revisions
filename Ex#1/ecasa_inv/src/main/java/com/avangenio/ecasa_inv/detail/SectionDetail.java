package com.avangenio.ecasa_inv.detail;


import com.avangenio.ecasa_inv.domain.ProductType;
import com.avangenio.ecasa_inv.domain.Section;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SectionDetail {
	
	private Integer id;
	private String name;
	private double size;
    private ProductType productType;
    

    
	public SectionDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SectionDetail(String name, double size, ProductType productType) {
		super();
		this.name = name;
		this.size = size;
		this.productType = productType;
	}
	
	public SectionDetail(Section section) {
		this.id = section.getId();
		this.name = section.getSectionName();
		this.size = section.getSize();
		this.productType = section.getProductType();
		
	}
	
	public Section toSectionEntity() {
		return new Section(this.id, this.name, this.size, this.productType);
	}

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

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}


}
