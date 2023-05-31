package com.leepark.fli.model.service;

import java.util.List;

import com.leepark.fli.model.dto.Playlist;
// Service -> DAO의 DB 접근 기본 메서드를 조립해서 새로운 기능을 구현하는 것
public interface PlaylistService {
	Integer insert(Playlist playlist);
	List<Playlist> selectAll(String userId, Integer userSns);
	Integer delete(Integer playlistId);
//	List<PlaylistInfo> selectAll(String userId, Integer userSns);
}
