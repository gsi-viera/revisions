package com.avangenio.ecasa_inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.avangenio.ecasa_inv.domain.Section;

public interface SectionRepository extends JpaRepository<Section, Long>{
	
}
