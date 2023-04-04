package com.avangenio.ecasa_inv.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.avangenio.ecasa_inv.detail.SectionDetail;
import com.avangenio.ecasa_inv.domain.Section;
import com.avangenio.ecasa_inv.repository.SectionRepository;

@Service
public class SectionService {
	private SectionRepository sectionRepository;
	private InventoryService inventoryService;

	public SectionService(SectionRepository sectionRepository, InventoryService inventoryService) {
		this.sectionRepository = sectionRepository;
		this.inventoryService = inventoryService;
	}
	
	public SectionDetail saveSection(SectionDetail detail) {
		Section saved = sectionRepository.save(detail.toSectionEntity());
		return new SectionDetail(saved);
	}
	
	public SectionDetail findSectionById(long id) throws Exception{
		Optional<Section> optional = sectionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found section with id: " + id); 
		}
		return new SectionDetail(optional.get());
	}
	
	public List<SectionDetail> findAll() {
		List<SectionDetail> details = new LinkedList<SectionDetail>();
		List<Section> list = sectionRepository.findAll();
		for (Section section : list) {
			details.add(new SectionDetail(section));
		}
		return details;
	}
	
	public SectionDetail updateSection(long id, SectionDetail detail) throws Exception{
		Optional<Section> optional = sectionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found section with id: " + id); 
		}	
		Section toUpdate = 	optional.get();
		toUpdate.setSectionName(detail.getName());
		toUpdate.setSize(detail.getSize());
		toUpdate.setProductType(detail.getProductType()); 
		
		return new SectionDetail(sectionRepository.save(toUpdate));
	}
	
	public void removeSection(long id) throws Exception {
		Optional<Section> optional = sectionRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found section with id: " + id); 
		}
		if (inventoryService.countInventoriesBySection(id) > 0) {
			throw new Exception("Section with id " + id + " can't be deleted. Section has registeres products");
		}
		sectionRepository.deleteById(id);
	}

}
