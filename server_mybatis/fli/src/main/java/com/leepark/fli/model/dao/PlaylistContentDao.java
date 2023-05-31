package com.leepark.fli.model.dao;

import com.leepark.fli.model.dto.PlaylistContent;

public interface PlaylistContentDao {
	// 유저 플레이리스트에 노래 추가
    Integer insertMusic(PlaylistContent playlistContent);
    // 유저 플레이리스트의 노래 삭제
    Integer deleteMusic(PlaylistContent playlistContent);
    // 플레이리스트에 들어있는 노래 모두 삭제
    Integer deleteAll(Integer playlistId);
}
