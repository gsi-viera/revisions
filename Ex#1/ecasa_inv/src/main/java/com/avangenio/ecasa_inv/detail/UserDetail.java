package com.avangenio.ecasa_inv.detail;

import com.avangenio.ecasa_inv.domain.User;

public class UserDetail {
	private Integer id;
	private String username;
	private String password;
	
	private String roles;

	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetail(Integer id, String username, String password, String roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	
	public UserDetail(User user) {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		roles = user.getRoles().get(0).getRole();
		 for (int i = 1; i < user.getRoles().size(); i++) {
			 roles += ","+ user.getRoles().get(i).getRole();
		}
		
	}
	
	public User toEntity() {
		return new User(this.id, this.username,this.password);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	

}
