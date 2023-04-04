package com.avangenio.ecasa_inv.service;

import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.domain.EContainer;
import com.avangenio.ecasa_inv.domain.EProductType;
import com.avangenio.ecasa_inv.domain.ProductType;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class FillData {
	private ContainerService containerService;
	private ProductTypeService productTypeService;

	public FillData(ContainerService containerService, ProductTypeService productTypeService) {
		this.containerService = containerService;
		this.productTypeService = productTypeService;
	}
	
	/*@PostConstruct
	@Transactional
	public void fillData() {
		//Actions to create container types in DB.
		Container container1 = new Container(EContainer.CARDBOARD);
		Container container2 = new Container(EContainer.GLASS);
		Container container3 = new Container(EContainer.NYLON);
		Container container4 = new Container(EContainer.PLASTIC);
		
		containerService.saveContainer(container1);
		containerService.saveContainer(container2);
		containerService.saveContainer(container3);
		containerService.saveContainer(container4);
		
		//Actions to create product types in DB.
		ProductType productType1 = new ProductType(EProductType.APPLIANCE);
		ProductType productType2 = new ProductType(EProductType.CLEANLINESS);
		ProductType productType3 = new ProductType(EProductType.CLOTHES);
		ProductType productType4 = new ProductType(EProductType.MEAT);
		
		productTypeService.savePType(productType1);
		productTypeService.savePType(productType2);
		productTypeService.savePType(productType3);
		productTypeService.savePType(productType4);
	}*/

}
