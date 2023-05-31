package com.leepark.fli.model.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@Table(name="playlistContents")
public class PlaylistContent {
    private Integer playlistId;
    private Integer musicId; // 0 or 1
    
    

	public PlaylistContent(Integer playlistId, Integer musicId) {
		super();
		this.playlistId = playlistId;
		this.musicId = musicId;
	}
	
	public PlaylistContent() {}

	@Override
	public String toString() {
		return "PlaylistContent [playlistId=" + playlistId + ", musicId=" + musicId + "]";
	}
 
}