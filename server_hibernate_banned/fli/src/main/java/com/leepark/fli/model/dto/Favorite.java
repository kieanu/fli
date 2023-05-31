package com.leepark.fli.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name="favorites")
public class Favorite {
	@Id
    private Integer favoriteId;
    private String userId;
    private Integer userSns;
    private Integer musicId;
    
	public Favorite() {}

	public Favorite(Integer favoriteId, String userId, Integer userSns, Integer musicId) {
		super();
		this.favoriteId = favoriteId;
		this.userId = userId;
		this.userSns = userSns;
		this.musicId = musicId;
	}
	
	public Favorite(String userId, Integer userSns, Integer musicId) {
		super();
		this.userId = userId;
		this.userSns = userSns;
		this.musicId = musicId;
	}

	@Override
	public String toString() {
		return "Farvorite [favoriteId=" + favoriteId + ", userId=" + userId + ", userSns=" + userSns + ", musicId="
				+ musicId + "]";
	}
    
}