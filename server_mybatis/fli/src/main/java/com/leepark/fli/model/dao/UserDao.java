package com.leepark.fli.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.leepark.fli.model.dto.User;

public interface UserDao {
    User select(@Param("userId") String userId,@Param("userSns") Integer userSns);

    List<User> selectAll();
    
    int insert(User user); // 플레이리스트 정보가 추가 될 때 User 정보가 추가로 생성됨
    
    int delete(@Param("userId") String userId,@Param("userSns") Integer userSns); // 플레이리스트 정보가 삭제 될 때 User정보가 삭제됨
    
    boolean isUsed(User user);
    
    int update(User user);
    
//    int update(User user); // 플레이리스트의 ID로 바로 플레이리스트 db에 접근해서 목록 수정하면 됨
}
