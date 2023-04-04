package com.avangenio.ecasa_inv.filter;

import java.util.List;


public class ProductSearch {
	
	private String dataOption;
	private List<SearchCriteria> searchCriterias;
	
	
	public ProductSearch(String dataOption, List<SearchCriteria> searchCriterias) {
		super();
		this.dataOption = dataOption;
		this.searchCriterias = searchCriterias;
	}
	public ProductSearch() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDataOption() {
		return dataOption;
	}
	public void setDataOption(String dataOption) {
		this.dataOption = dataOption;
	}
	public List<SearchCriteria> getSearchCriterias() {
		return searchCriterias;
	}
	public void setSearchCriterias(List<SearchCriteria> searchCriterias) {
		this.searchCriterias = searchCriterias;
	}
	
	

}
