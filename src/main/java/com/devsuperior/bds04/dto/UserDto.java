package com.devsuperior.bds04.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.devsuperior.bds04.entities.User;


public class UserDto implements Serializable {
	
	private static final long serialVersionUID =1L;	
	
	private Long id;
	
	
	@Email(message="Favor entrar com um email válido.")
	private String email;
	
	private List<RoleDto> roles = new ArrayList<>();

	public UserDto() {}
	
	public UserDto(Long id, String firstName, String lastName, String email) {
		this.id = id;
		this.email = email;
	}

	public UserDto(User user) {
	    this.id =user.getId();
		this.email = user.getEmail();
		user.getRoles().forEach(role -> this.roles.add(new RoleDto(role)));

	}

	
	public List<RoleDto> getRoles() {
		return roles;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
