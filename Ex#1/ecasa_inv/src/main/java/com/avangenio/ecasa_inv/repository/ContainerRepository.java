package com.avangenio.ecasa_inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avangenio.ecasa_inv.domain.Container;
import com.avangenio.ecasa_inv.domain.EContainer;

public interface ContainerRepository extends JpaRepository<Container, Long>{
	@Query("SELECT c FROM Container c where c.containerType = :type")
	public Container getContainerByName(@Param("type")EContainer type);

}
