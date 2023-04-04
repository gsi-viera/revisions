package com.avangenio.ecasa_inv.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.avangenio.ecasa_inv.domain.Product;

public class ProdSpecificationBuilder {
	private final List<SearchCriteria> params;

	public ProdSpecificationBuilder(List<SearchCriteria> params) {
		super();
		this.params = params;
	}

	public final ProdSpecificationBuilder with(String key, 
			String operation, Object value){
		params.add(new SearchCriteria(key, operation, value));
		return this;
	}	

	public final ProdSpecificationBuilder with(SearchCriteria
			searchCriteria){
		params.add(searchCriteria);
		return this;
	}


	public Specification<Product> build(){
		if(params.size() == 0){
			return null;
		}

		Specification<Product> result = 
				new ProductSpecification(params.get(0));
		for (int idx = 1; idx < params.size(); idx++){
			SearchCriteria criteria = params.get(idx);
			result =  SearchOperation.getDataOption(criteria
					.getDataOption()) == SearchOperation.ALL
					? Specification.where(result).and(new    
							ProductSpecification(criteria))
							: Specification.where(result).or(
									new ProductSpecification(criteria));
		}
		return result;
	}

	public ProdSpecificationBuilder() {
		this.params = new ArrayList<>();;
	}


}
