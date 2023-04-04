package com.avangenio.ecasa_inv.controller;


import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avangenio.ecasa_inv.config.JwtGenerator;
import com.avangenio.ecasa_inv.detail.UserDetail;
import com.avangenio.ecasa_inv.service.UserService;

@RestController
@RequestMapping("/ecasa/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtGenerator jwtGenerator;
	
	
	public UserController(UserService userService, JwtGenerator jwtGenerator) {
		super();
		this.userService = userService;
		this.jwtGenerator = jwtGenerator;
	}

	@GetMapping
	public List<UserDetail> getUsers() {
		return userService.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserDetail detail) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(detail)); 
			
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Already exist a user with user_name: " + detail.getUsername()+"!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserDetail user) {
		try {
			if (user.getUsername() == null || user.getPassword() == null) {
				throw new UserPrincipalNotFoundException("UserName or Password is Empty");
			}
			UserDetail userData = userService.findByUserNameAndPassword(user.getUsername(), user.getPassword());
			if (userData == null) {
				throw new UserPrincipalNotFoundException("UserName or Password is invalid");
			}
			return ResponseEntity.status(HttpStatus.OK).body(jwtGenerator.generateToken(userData)); 
			
		}catch (UserPrincipalNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new String(e.getMessage()));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable(value = "id") int id) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.findUserById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String(e.getMessage()));
		}
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") int id) {
		try {
			userService.removeUser(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(value = "id") int id, @RequestBody UserDetail detail) {
		try {
			UserDetail userDetail = userService.updateUser(id, detail);
			return ResponseEntity.status(HttpStatus.OK).body(userDetail);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Already exist a user with user_name: " + detail.getUsername()+"!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
		}
		
	}

}
