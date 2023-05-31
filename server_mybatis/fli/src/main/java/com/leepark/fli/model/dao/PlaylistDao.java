package com.leepark.fli.model.dao;

import java.util.List;

import com.leepark.fli.model.dto.Playlist;

public interface PlaylistDao {
	// 새로운 플레이리스트 생성
	Integer insert(Playlist playlist);
	
	// 플레이리스트 목록 가져오기
	List<Playlist> selectAll(String userId, Integer userSns);
	
	// 플레이리스트 삭제
	Integer delete(Integer playlistId);
	
//	// 플레이리스트 목록 가져오기
//	List<PlaylistInfo> selectAll(String userId, Integer userSns);
}
