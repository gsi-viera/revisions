package com.avangenio.ecasa_inv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avangenio.ecasa_inv.domain.SecurityRole;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Integer>{
	@Query("SELECT r FROM SecurityRole r where r.role = :role")
	public SecurityRole findRoleByName(@Param("role")String role);

}
