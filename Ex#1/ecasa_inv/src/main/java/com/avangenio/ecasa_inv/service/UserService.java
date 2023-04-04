package com.avangenio.ecasa_inv.service;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avangenio.ecasa_inv.detail.UserDetail;
import com.avangenio.ecasa_inv.domain.SecurityRole;
import com.avangenio.ecasa_inv.domain.User;
import com.avangenio.ecasa_inv.repository.SecurityRoleRepository;
import com.avangenio.ecasa_inv.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private SecurityRoleRepository roleRepository;
	
	//@Autowired
	//private BCryptPasswordEncoder bCryptEncoder;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		//this.bCryptEncoder = bCryptEncoder;
	}
	
	public UserDetail saveUser(UserDetail detail) {
		User user = detail.toEntity();
		//user.setPassword(bCryptEncoder.encode(detail.getPassword()));
		
		String []roles = detail.getRoles().split(",");
		List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
		for (int i = 0; i < roles.length; i++) {
			securityRoles.add(roleRepository.findRoleByName(roles[i]));
		}
		user.setRoles(securityRoles);
		
		return new UserDetail(userRepository.save(user));
	}
	
	public UserDetail findUserByUserName(String username) {
		return new UserDetail(userRepository.findByUsername(username));
	}
	
	public UserDetail findByUserNameAndPassword(String username, String password) throws UserPrincipalNotFoundException {
		User user = userRepository.findByUserNameAndPassword(username, password);
		if (user == null) {
			throw new UserPrincipalNotFoundException("Invalid id and password");
		}
		return new UserDetail(user);
	}
	
	public List<UserDetail> findAll() {
		List<UserDetail> details = new LinkedList<UserDetail>();
		List<User> users = userRepository.findAll();
		
		for (User user : users) {
			details.add(new UserDetail(user));
		}
		return details;
	}
	
	public UserDetail findUserById(int id) throws Exception {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty())
			throw new Exception("Not found user with id: " + id); 
		return new UserDetail(optional.get());
	}
	
	
	public UserDetail updateUser(int id, UserDetail detail) throws Exception{
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found user with id: " + id); 
		}	
		User toUpdate = optional.get();
		toUpdate.setUsername(detail.getUsername());
		toUpdate.setPassword(detail.getPassword());

		String []roles = detail.getRoles().split(",");
		List<SecurityRole> securityRoles = new ArrayList<SecurityRole>();
		for (int i = 0; i < roles.length; i++) {
			securityRoles.add(roleRepository.findRoleByName(roles[i]));
		}
		toUpdate.setRoles(securityRoles);
		
		return new UserDetail(userRepository.save(toUpdate));
	}
	
	public void removeUser(int id) throws Exception {
		Optional<User> optional = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("Not found user with id: " + id); 
		}
		userRepository.deleteById(id);
	}

}
