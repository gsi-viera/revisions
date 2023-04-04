package com.avangenio.ecasa_inv.filter;

import java.util.List;

public class ProductSearchFilter {
	private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
    
    
	public ProductSearchFilter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductSearchFilter(List<SearchCriteria> searchCriteriaList, String dataOption) {
		super();
		this.searchCriteriaList = searchCriteriaList;
		this.dataOption = dataOption;
	}
	public List<SearchCriteria> getSearchCriteriaList() {
		return searchCriteriaList;
	}
	public void setSearchCriteriaList(List<SearchCriteria> searchCriteriaList) {
		this.searchCriteriaList = searchCriteriaList;
	}
	public String getDataOption() {
		return dataOption;
	}
	public void setDataOption(String dataOption) {
		this.dataOption = dataOption;
	}
    
    

}
