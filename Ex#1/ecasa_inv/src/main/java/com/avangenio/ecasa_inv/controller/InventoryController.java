package com.avangenio.ecasa_inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avangenio.ecasa_inv.detail.InventoryDetail;
import com.avangenio.ecasa_inv.detail.ProductDetail;
import com.avangenio.ecasa_inv.detail.SectionDetail;
import com.avangenio.ecasa_inv.service.InventoryService;
import com.avangenio.ecasa_inv.service.ProductService;
import com.avangenio.ecasa_inv.service.SectionService;

@RestController
@RequestMapping("/ecasa/inventories")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@Autowired
	private ProductService productService;
	@Autowired
	private SectionService sectionService;
	
	@GetMapping
	public List<InventoryDetail> getAll() {
		return inventoryService.findAll();
	}
	
	@PostMapping("{sectionId}/product/{productId}")
	public ResponseEntity<?> create(@PathVariable(value = "sectionId") long sectionId, @PathVariable(value = "productId") long productId, @RequestBody InventoryDetail detail) {
		try {
			SectionDetail sectionDetail = sectionService.findSectionById(sectionId);
			ProductDetail productDetail = productService.findProductById(productId);
			detail.setSection(sectionDetail);
			detail.setProduct(productDetail);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveInventory( detail)); 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getInventory(@PathVariable(value = "id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.findInventoryById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateInventory(@PathVariable(value = "id") long id, @RequestBody InventoryDetail detail) {
		try {
			
			InventoryDetail inventoryDetail = inventoryService.updateInventory(id, detail);
			return ResponseEntity.status(HttpStatus.OK).body(inventoryDetail);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
		
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
		try {
			inventoryService.removeInventory(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}
	

}
