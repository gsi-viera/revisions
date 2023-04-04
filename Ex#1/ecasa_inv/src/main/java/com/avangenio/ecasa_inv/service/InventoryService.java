package com.avangenio.ecasa_inv.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.detail.InventoryDetail;
import com.avangenio.ecasa_inv.domain.Inventory;
import com.avangenio.ecasa_inv.repository.InventoryRepository;

@Service
public class InventoryService {
	private InventoryRepository inventoryRepository;
	
	public InventoryService(InventoryRepository inventoryRepository) {
		super();
		this.inventoryRepository = inventoryRepository;
	}
	
	public int countInventoriesBySection(long sectionId) {
		return inventoryRepository.countInventoriesBySection(sectionId);
		
	}


	public InventoryDetail saveInventory(InventoryDetail detail) throws Exception {
		Inventory saved = inventoryRepository.save(detail.toEntity());
		return new InventoryDetail(saved);
	}
	
	public List<InventoryDetail> findAll() {
		List<InventoryDetail> details = new LinkedList<InventoryDetail>();
		List<Inventory> list = inventoryRepository.findAll();
		
		for (Inventory inventory : list) {
			details.add(new InventoryDetail(inventory));
		}
		return details;
	}
	
	public InventoryDetail findInventoryById(long id) throws Exception {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		if(optional.isEmpty())
			throw new Exception("Not found inventory with id: " + id); 
		return new InventoryDetail(optional.get());
	}
	
	public InventoryDetail updateInventory(long id,InventoryDetail detail) throws Exception{
		Optional<Inventory> optional = inventoryRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found inventory with id: " + id); 
		}	
		Inventory toUpdate = optional.get();
		toUpdate.setStock(detail.getStock());
		return new InventoryDetail(inventoryRepository.save(toUpdate));
	}
	
	public void removeInventory(long id) throws Exception {
		Optional<Inventory> optional = inventoryRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found inventory with id: " + id); 
		}
		inventoryRepository.deleteById(id);
	}
	

}
