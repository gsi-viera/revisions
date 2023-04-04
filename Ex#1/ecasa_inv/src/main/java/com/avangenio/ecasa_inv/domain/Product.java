package com.avangenio.ecasa_inv.domain;

import java.math.BigDecimal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	private String name;
	private String color;
	private String lote;
	private double size;
	private boolean fragile;
    private BigDecimal price;
    
    @JoinColumn(name = "containerId")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    private Container container;
    
    @OneToOne(mappedBy = "product")
    private Inventory inventory;
    
	public Product(Integer id,String name, String color, String lote, double size, boolean fragile, BigDecimal price, Container container) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.lote = lote;
		this.size = size;
		this.fragile = fragile;
		this.price = price;
		this.container = container;
	}
	
	public Product(Integer id,String name, String color, String lote, double size, boolean fragile, BigDecimal price, Container container,Inventory inventory) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.lote = lote;
		this.size = size;
		this.fragile = fragile;
		this.price = price;
		this.container = container;
		this.inventory = inventory;
	}


	public Product() {
		super();
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

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
    
}
