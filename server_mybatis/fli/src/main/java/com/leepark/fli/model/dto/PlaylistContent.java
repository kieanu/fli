package com.leepark.fli.model.dto;

import lombok.*;

@Getter
@Setter
public class PlaylistContent {
    private Integer playlistId;
    private Integer musicId;

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