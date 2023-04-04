package com.avangenio.ecasa_inv.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.avangenio.ecasa_inv.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u where u.username = :username")
	public User findByUsername(@Param("username")String username);
	
	@Query("SELECT u FROM User u where u.username = :username and u.password = :password")
	public User findByUserNameAndPassword(@Param("username")String username, @Param("password")String password);
}
