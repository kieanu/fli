package com.leepark.fli.model.dto;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.leepark.fli.model.dtoId.UserId;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name="users")
public class User {
	@EmbeddedId
    private UserId userId;
	@Column(name = "name")
    private String name;
    
    

	public User(UserId userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}



	public User() {}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + "]";
	}

}