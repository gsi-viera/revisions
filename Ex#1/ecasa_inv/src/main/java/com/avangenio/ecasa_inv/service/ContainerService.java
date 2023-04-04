package com.avangenio.ecasa_inv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.domain.EContainer;
import com.avangenio.ecasa_inv.repository.ContainerRepository;

@Service
public class ContainerService {
	private ContainerRepository containerRepository;

	public ContainerService(ContainerRepository containerRepository) {
		super();
		this.containerRepository = containerRepository;
	}
	
	public Container saveContainer(Container container) {
		Container saved = containerRepository.save(container);
		return saved;
	}
	
	public List<Container> findAllContiners() {
		List<Container> containers = containerRepository.findAll();
		return containers;
	}
	
	public Container findContainerById(long id) {
		Container container = new Container();
		Optional<Container> optional = containerRepository.findById(id);
		if (!optional.isEmpty()) 
			container = optional.get();
		
		return container;
	}
	
	public Container getContainerTypeByName(EContainer container) {
		return containerRepository.getContainerByName(container);
	}
	
	
	public void removeContainer(long id) {
		containerRepository.deleteById(id);
	}

}
