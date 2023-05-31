package com.leepark.fli.model.service;

import com.leepark.fli.model.dto.PlaylistContent;

// Service -> DAO의 DB 접근 기본 메서드를 조립해서 새로운 기능을 구현하는 것
public interface PlaylistContentService {
	// 유저 플레이리스트에 노래 추가
    Integer insertMusic(PlaylistContent playlistContent);
    Integer deleteMusic(PlaylistContent playlistContent);
    Integer deleteAll(Integer playlistId);
}
