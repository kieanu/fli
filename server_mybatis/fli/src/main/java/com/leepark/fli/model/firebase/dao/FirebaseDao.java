package com.leepark.fli.model.firebase.dao;

import com.leepark.fli.model.dto.User;

public interface FirebaseDao {
	String select(String userId, Integer userSns);
	String selectAll();
	Integer addToken(User user); 
}
