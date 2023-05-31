package com.leepark.fli.model.service;

import java.util.List;
import java.util.Map;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.dtoId.UserId;

// Service -> DAO의 DB 접근 기본 메서드를 조립해서 새로운 기능을 구현하는 것
public interface UserService {

	// SNS 로그인 한다. -> 최초 접속한 계정이면 DB에 유저 정보 Insert 
	// (로그인 방지 체크는 sns 토큰을 가져올 때 해당 SNS 에서 처리가 될 거임)
    public Boolean insert(User user);
    
    public User select(UserId userId);
    
    public Boolean delete(UserId userId);
    
    public List<User> selectAll();
    
    public Boolean isUsed(UserId userId);   
}
