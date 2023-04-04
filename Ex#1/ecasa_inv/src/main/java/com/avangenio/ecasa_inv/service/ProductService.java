package com.avangenio.ecasa_inv.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.detail.ProductDetail;
import com.avangenio.ecasa_inv.detail.SectionDetail;
import com.avangenio.ecasa_inv.domain.Product;
import com.avangenio.ecasa_inv.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private SectionService sectionService;

	public ProductService(ProductRepository productRepository, SectionService sectionService) {
		this.productRepository = productRepository;
		this.sectionService = sectionService;
	}

	public ProductDetail saveProduct(ProductDetail detail) {
		Product saved = productRepository.save(detail.toProductEntity());
		String section = getSectionName(saved);
		ProductDetail productDetail = new ProductDetail(saved);
		productDetail.setSection(section);
		return productDetail;
	}

	public ProductDetail findProductById(long id) throws Exception {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found product with id: " + id); 
		}
		Product product = optional.get();
		ProductDetail productDetail = new ProductDetail(product);
		String section = getSectionName(product);
		if (section != null) 
			productDetail.setSection(section);

		return productDetail;
	}

	public List<ProductDetail> findAll(){
		List<ProductDetail> details = new LinkedList<ProductDetail>();
		List<Product> list = productRepository.findAll();

		for (Product product : list) {
			ProductDetail productDetail = new ProductDetail(product);
			String section = getSectionName(product);
			if (section != null) 
				productDetail.setSection(section);

			details.add(productDetail);
		}
		return details;
	}


	public ProductDetail updateProduct(long id, ProductDetail detail) throws Exception{
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found product with id: " + id); 
		}	
		Product toUpdate = 	optional.get();
		toUpdate.setName(detail.getName());
		toUpdate.setSize(detail.getSize());
		toUpdate.setColor(detail.getColor()); 
		toUpdate.setFragile(detail.isFragile());
		toUpdate.setLote(detail.getLote());
		toUpdate.setPrice(detail.getPrice());
		toUpdate.setContainer(detail.getContainer());

		return new ProductDetail(productRepository.save(toUpdate));
	}


	public void removeProduct(long id) throws Exception {
		Optional<Product> optional = productRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found product with id: " + id); 
		}
		productRepository.deleteById(id);
	}

	public List<ProductDetail> findBySearchCriteria(Specification<Product> spec, Pageable page){
		Page<Product> searchResult = productRepository.findAll(spec, page);
		List<Product> products = searchResult.toList();
		List<ProductDetail> details = new ArrayList<ProductDetail>();

		for (Product product : products) {
			ProductDetail productDetail = new ProductDetail(product);
			String section = getSectionName(product);
			if (section != null) 
				productDetail.setSection(section);
			details.add(productDetail);
		}

		return details;
	}
	
	private String getSectionName(Product product) {
		String name = null;
		try {
			if (product.getInventory()!= null) {
				SectionDetail section;
				section = sectionService.findSectionById(product.getInventory().getId());
				name = section.getName();
			}
			
		} catch (Exception e) {
			name = null;
		}
		return name;
	}

}
