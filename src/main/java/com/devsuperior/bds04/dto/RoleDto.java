package com.devsuperior.bds04.dto;

import java.io.Serializable;

import com.devsuperior.bds04.entities.Role;



public class RoleDto implements Serializable {
	
	private static final long serialVersionUID =1L;
	
	
	private Long id;
	private String authority;
	
	public RoleDto(Long id, String authority) {
		super();
		this.id = id;
		this.authority = authority;
	}

	public RoleDto(Role role) {
		this.id = role.getId();
		this.authority = role.getAuthority();
	}
	
	public RoleDto() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}
