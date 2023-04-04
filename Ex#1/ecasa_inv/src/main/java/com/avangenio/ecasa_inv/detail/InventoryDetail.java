package com.avangenio.ecasa_inv.detail;

import com.avangenio.ecasa_inv.domain.Inventory;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryDetail {
	private Integer id;
	private SectionDetail section;
	private ProductDetail product;
	private int stock;

	
	
	public InventoryDetail(SectionDetail section, ProductDetail product, int stock) {
		super();
		this.section = section;
		this.product = product;
		this.stock = stock;
	}

	public InventoryDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InventoryDetail(Inventory inventory) {
		this.id = inventory.getId();
		this.section = new SectionDetail(inventory.getSection());
		this.product = new ProductDetail(inventory.getProduct());
		this.stock = inventory.getStock();
	}
	
	public Inventory toEntity() {
		return new Inventory(this.id, this.stock, this.section.toSectionEntity(),this.product.toProductEntity());
	}

	
	public SectionDetail getSection() {
		return section;
	}

	public void setSection(SectionDetail section) {
		this.section = section;
	}

	public ProductDetail getProduct() {
		return product;
	}

	public void setProduct(ProductDetail product) {
		this.product = product;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
