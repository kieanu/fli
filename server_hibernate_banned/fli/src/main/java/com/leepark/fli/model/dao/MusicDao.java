package com.leepark.fli.model.dao;

import java.util.List;

import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.dtoId.UserId;

public interface MusicDao {
	// 특정 노래를 뽑아옴(클라이언트가 사용할 일이 있나?)
    Music select(Integer musicId);

    List<Music> selectAll();
    
    // 장르코드로 노래를 뽑아옴
    List<Music> selectByGenre(Integer genreId);
    
    // 유저 id로 유저가 좋아요한 목록을 뽑아옴
    List<Music> selectByFavorite(User user);
    
}
