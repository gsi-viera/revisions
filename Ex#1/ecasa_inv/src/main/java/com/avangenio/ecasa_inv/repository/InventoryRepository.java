package com.avangenio.ecasa_inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avangenio.ecasa_inv.domain.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
	@Query("SELECT COUNT(*) FROM Inventory i where i.section.id = :sectionId")
	public int countInventoriesBySection(@Param("sectionId")long sectionId);


}
