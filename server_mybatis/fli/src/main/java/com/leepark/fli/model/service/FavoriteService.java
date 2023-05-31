package com.leepark.fli.model.service;

import java.util.List;
import java.util.Map;

import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.User;

// Service -> DAO의 DB 접근 기본 메서드를 조립해서 새로운 기능을 구현하는 것
public interface FavoriteService {

    public List<Favorite> selectAll(String userId, Integer userSns); // 유저가 좋아요한 노래 전부 가져오기 
    
    public int insert(Favorite favorite); // 좋아요 누르면 추가됨
    
    public int delete(Favorite favorite); // 좋아요 한번 더 누르면 삭제됨
    
    public Boolean isFavorite(String userId, Integer userSns, Integer musicId); // 이미 좋아요한 노래인지 확인
}
