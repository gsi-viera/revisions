package com.avangenio.ecasa_inv.detail;

import java.math.BigDecimal;

import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.domain.Product;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductDetail {

	private Integer id;
	private String name;
	private String color;
	private String lote;
	private double size;
	private boolean fragile;
    private BigDecimal price;
    private Container container;
    private String section;
    private int stock;
    
    
	public ProductDetail() {
		super();
	}
	
	public ProductDetail(Product product) {
		this.id = product.getId();
		this.color = product.getColor();
		this.fragile = product.isFragile();
		this.lote = product.getLote();
		this.name = product.getName();
		this.price = product.getPrice();
		this.size = product.getSize();
		
		this.container = product.getContainer();
		
		if (product.getInventory()!= null) {
			this.stock = product.getInventory().getStock();
		}
	}
    
	public Product toProductEntity() {
		return new Product(this.id, this.name,this.color, this.lote, this.size, this.fragile, this.price, this.container);
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	

}
