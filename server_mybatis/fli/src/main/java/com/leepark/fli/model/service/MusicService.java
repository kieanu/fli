package com.leepark.fli.model.service;

import java.util.List;

import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.Like;
import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;

// Service -> DAO의 DB 접근 기본 메서드를 조립해서 새로운 기능을 구현하는 것
public interface MusicService {

	// 특정 노래를 뽑아옴(클라이언트가 사용할 일이 있나?)
    Music select(Integer musicId);

    List<Music> selectAll();
    
    List<Music> selectAllByPaging(Integer lastId, Integer count);
    
    // 장르코드로 노래를 뽑아옴
    List<Music> selectByGenre(Integer genreId);
    
    // 유저 id로 유저가 좋아요한 목록을 뽑아옴
    List<Music> selectByFavorite(String userId, Integer userSns);
    
    // 유저 id로 유저가 등록한한 플레이리스트 목록을 뽑아옴
    List<Music> selectByPlaylist(Integer playlistId);
    
    // 랭크 순 조회
    List<Music> selectAllByRank();
    
    // 랭크 순 조회 by 페이징
    List<Music> selectAllByRankByPaging(Integer lastRank, Integer count);
    
    // 랭크 순 조회 (n순위 노래 목록 뽑아오기)
    List<Music> selectByRank(Integer ranking);
    
    // 랭크 순 조회 (keyword를 포함하는 노래 목록 뽑아오기)
    List<Music> selectAllBySearch(String keyword);
    
    // 좋아요
    Integer updateLike(Favorite favorite);
    
}
