package com.avangenio.ecasa_inv.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.avangenio.ecasa_inv.detail.ProductDetail;
import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.filter.ProdSpecificationBuilder;
import com.avangenio.ecasa_inv.filter.ProductSearch;
import com.avangenio.ecasa_inv.filter.SearchCriteria;
import com.avangenio.ecasa_inv.service.ContainerService;
import com.avangenio.ecasa_inv.service.ProductService;

@RestController
@RequestMapping("/ecasa/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ContainerService containerService;

	@GetMapping
	public List<ProductDetail> getProducts() {
		return productService.findAll();
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDetail detail) {
		try {
			Container container = containerService.getContainerTypeByName(detail.getContainer().getContainerType());
			detail.setContainer(container);
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(detail)); 

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProduct(@PathVariable(value = "id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.findProductById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(value = "id") long id, @RequestBody ProductDetail detail) {
		try {
			if (detail.getContainer() != null) {
				Container container = containerService.getContainerTypeByName(detail.getContainer().getContainerType());
				detail.setContainer(container);
			}
			ProductDetail productDetail = productService.updateProduct(id, detail);
			return ResponseEntity.status(HttpStatus.OK).body(productDetail);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") long id) {
		try {
			productService.removeProduct(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}

	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestParam(name = "pageNum", defaultValue = "0") int pageNum, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, @RequestBody ProductSearch search) {
		ProdSpecificationBuilder builder = new ProdSpecificationBuilder();
		List<SearchCriteria> criteriaList = search.getSearchCriterias();
		if(criteriaList != null){
			for (SearchCriteria searchCriteria : criteriaList) {
				searchCriteria.setDataOption(search.getDataOption());
				builder.with(searchCriteria);
			}
		}
		
		Pageable page = PageRequest.of(0, 10, Sort.by("name").ascending().and(Sort.by("container")).ascending());
		
		List<ProductDetail> productDetails = productService.findBySearchCriteria(builder.build(), page);
		return ResponseEntity.status(HttpStatus.OK).body(productDetails);
	}


}
