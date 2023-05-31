package com.leepark.fli.model.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
public class User {
    private String userId;
    private Integer userSns;
    private String name;
    private String token;
    
	public User(String userId, Integer userSns, String name) {
		super();
		this.userId = userId;
		this.userSns = userSns;
		this.name = name;
	}

	public User(String userId, Integer userSns, String name, String token) {
		super();
		this.userId = userId;
		this.userSns = userSns;
		this.name = name;
		this.token = token;
	}

	public User() {}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userSns=" + userSns + ", name=" + name + ", token=" + token + "]";
	}

}