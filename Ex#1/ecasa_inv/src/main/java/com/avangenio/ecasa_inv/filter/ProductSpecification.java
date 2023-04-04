package com.avangenio.ecasa_inv.filter;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.avangenio.ecasa_inv.domain.Product;
import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.domain.Inventory;
import com.avangenio.ecasa_inv.domain.Section;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ProductSpecification implements Specification<Product>{
	private final SearchCriteria searchCriteria;

	public ProductSpecification(SearchCriteria searchCriteria) {
		super();
		this.searchCriteria = searchCriteria;
	}

	@Override
	public Predicate toPredicate(Root<Product> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		String strToSearch = searchCriteria.getValue()
				.toString().toLowerCase();

		switch (Objects.requireNonNull( SearchOperation.getSimpleOperation(searchCriteria.getOperation()))){
		case CONTAINS:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.like(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.like(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.like(cb.lower(root
					.get(searchCriteria.getFilterKey())), 
					"%" + strToSearch + "%");

		case DOES_NOT_CONTAIN:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.notLike(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.notLike(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.notLike(cb.lower(root
					.get(searchCriteria.getFilterKey())), 
					"%" + strToSearch + "%");

		case GREATER_THAN:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.greaterThan(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.greaterThan(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.greaterThan(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);
		case GREATER_THAN_EQUAL:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.greaterThanOrEqualTo(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.greaterThanOrEqualTo(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.greaterThanOrEqualTo(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);

		case LESS_THAN:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.lessThan(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.lessThan(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.lessThan(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);

		case LESS_THAN_EQUAL:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.lessThanOrEqualTo(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.lessThanOrEqualTo(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.lessThanOrEqualTo(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);

		case EQUAL:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.equal(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.equal(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.equal(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);
		case NOT_EQUAL:
			if(searchCriteria.getFilterKey().equals("containerType"))
			{
				return cb.notEqual(cb.lower(containerJoin(root).
						<String>get(searchCriteria.getFilterKey())),strToSearch );
			}else if (searchCriteria.getFilterKey().equals("sectionName")) {
				return cb.notEqual(cb.lower(sectionJoin(root).
						<String>get(searchCriteria.getFilterKey())),
						"%" + strToSearch + "%");
			}
			return cb.notEqual(cb.lower(root
					.get(searchCriteria.getFilterKey())), strToSearch);

		case NOT_NULL:
			return cb.isNotNull(cb.lower(root
					.get(searchCriteria.getFilterKey())));
		}
		return null;
	}

	private Join<Product, Container> containerJoin(Root<Product>root){
		return root.join("container");
	}
	
	private Join<Inventory, Section> sectionJoin(Root<Product>root) {
		Join<Product, Inventory> inventory = root.join("inventory");
        Join<Inventory, Section> section = inventory.join("section");
        
        return section;
	}

}
