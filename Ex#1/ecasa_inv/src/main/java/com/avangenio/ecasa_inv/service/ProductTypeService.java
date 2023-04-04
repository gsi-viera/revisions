package com.avangenio.ecasa_inv.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.domain.EProductType;
import com.avangenio.ecasa_inv.domain.ProductType;
import com.avangenio.ecasa_inv.repository.ProductTypeRepository;

@Service
public class ProductTypeService {
	private ProductTypeRepository productTypeRepository;

	public ProductTypeService(ProductTypeRepository productTypeRepository) {
		super();
		this.productTypeRepository = productTypeRepository;
	}
	
	public ProductType savePType(ProductType productType) {
		ProductType saved = productTypeRepository.save(productType);
		return saved;
	}
	
	public List<ProductType> findAllPTypes() {
		List<ProductType> productTypes = productTypeRepository.findAll();
		return productTypes;
	}
	
	public ProductType findPTypeById(long id) {
		ProductType productType = new ProductType();
		Optional<ProductType> optional = productTypeRepository.findById(id);
		if (!optional.isEmpty()) 
			productType = optional.get();
		
		return productType;
	}
	
	public ProductType getProductTypeByName(EProductType productType) throws Exception {
		return productTypeRepository.getProductTypeByName(productType);
	}
	
	public void removePType(long id) {
		productTypeRepository.deleteById(id);
	}

}
