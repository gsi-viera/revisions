package com.avangenio.ecasa_inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avangenio.ecasa_inv.domain.EProductType;
import com.avangenio.ecasa_inv.domain.ProductType;


public interface ProductTypeRepository extends JpaRepository<ProductType, Long>{
	
	@Query("SELECT p FROM ProductType p where p.productType = :type")
	public ProductType getProductTypeByName(@Param("type")EProductType type);

}
