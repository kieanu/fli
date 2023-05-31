package com.leepark.fli.model.dao;

import java.util.List;
import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.User;

public interface FavoriteDao {
	// String userId, String userSns, Integer musicId
    List<Favorite> selectAll(String userId, Integer userSns);
    
    Favorite select(String userId, Integer userSns, Integer musicId);
    
    int insert(Favorite favorite); // 플레이리스트 정보가 추가 될 때 User 정보가 추가로 생성됨
    
    int delete(Favorite favorite); // 플레이리스트 정보가 삭제 될 때 User정보가 삭제됨
    
}
