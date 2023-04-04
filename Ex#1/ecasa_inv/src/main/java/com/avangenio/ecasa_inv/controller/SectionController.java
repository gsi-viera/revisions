package com.avangenio.ecasa_inv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avangenio.ecasa_inv.config.JwtGenerator;
import com.avangenio.ecasa_inv.detail.SectionDetail;
import com.avangenio.ecasa_inv.domain.ProductType;
import com.avangenio.ecasa_inv.service.ProductTypeService;
import com.avangenio.ecasa_inv.service.SectionService;
import com.avangenio.ecasa_inv.service.UserService;

@RestController
@RequestMapping("/ecasa/sections")
public class SectionController {
	
	@Autowired
	private SectionService sectionService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductTypeService productTypeService;
	
	@Autowired
	private JwtGenerator generator;
	
	
	
	public SectionController(SectionService sectionService, UserService userService,
			ProductTypeService productTypeService, JwtGenerator generator) {
		super();
		this.sectionService = sectionService;
		this.userService = userService;
		this.productTypeService = productTypeService;
		this.generator = generator;
	}

	@GetMapping
	public List<SectionDetail> getSections() {
		return sectionService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody SectionDetail detail) {
		try {
			ProductType productType = productTypeService.getProductTypeByName(detail.getProductType().getProductType());
			detail.setProductType(productType);
			return ResponseEntity.status(HttpStatus.CREATED).body(sectionService.saveSection(detail));  
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSection(@PathVariable(value = "id") long id) {
		try {
			SectionDetail sectionDetail = sectionService.findSectionById(id);
			return ResponseEntity.status(HttpStatus.FOUND).body(sectionDetail);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateSection(@PathVariable(value = "id") long id, @RequestBody SectionDetail detail) {
		try {
			ProductType productType = productTypeService.getProductTypeByName(detail.getProductType().getProductType());
			detail.setProductType(productType);
			
			SectionDetail sectionDetail = sectionService.updateSection(id, detail);
			return ResponseEntity.status(HttpStatus.OK).body(sectionDetail);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable(value = "id") long id) {
		try {
			String userName = generator.extractAllClaims(token.substring(7)).getSubject();
			String roles = userService.findUserByUserName(userName).getRoles();
			
			if (!roles.contains("ADMIN")) {
				throw new Exception("User is not authorized to make this action!");
			}
			sectionService.removeSection(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}
	

}
