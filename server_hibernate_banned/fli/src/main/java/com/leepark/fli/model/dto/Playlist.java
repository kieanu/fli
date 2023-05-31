package com.leepark.fli.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Entity
@Table(name="playlists")
public class Playlist {
	@Id
    private Integer playlistId;
    private String userId;
    private Integer userSns;
    
    
	public Playlist(Integer playlistId, String userId, Integer userSns) {
		super();
		this.playlistId = playlistId;
		this.userId = userId;
		this.userSns = userSns;
	}
    
	public Playlist(String userId, Integer userSns) {
		super();
		this.userId = userId;
		this.userSns = userSns;
	}

	public Playlist() {}

	@Override
	public String toString() {
		return "Playlist [playlistId=" + playlistId + ", userId=" + userId + ", userSns=" + userSns + "]";
	}

}