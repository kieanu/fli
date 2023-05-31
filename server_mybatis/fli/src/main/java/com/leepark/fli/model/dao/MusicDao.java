package com.leepark.fli.model.dao;

import java.util.List;

import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.Music;

public interface MusicDao {
	// 특정 노래를 뽑아옴(클라이언트가 사용할 일이 있나?)
    Music select(Integer musicId);

    // 노래 모두 뽑아옴
    List<Music> selectAll();
    
    // 노래를 1페이지 단위로 뽑아옴
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
    
    // 노래 조회 (keyword를 포함하는 노래 목록 뽑아오기)
    List<Music> selectAllBySearch(String keyword);
    
    // 좋아요(+1) or 좋아요 해제(-1)
    Integer updateLike(Favorite favorite);
    
    // 배치 작업 좋아요 순으로 랭크 변경하기
	void batchRanking();
	
	Integer selectCount();
}
