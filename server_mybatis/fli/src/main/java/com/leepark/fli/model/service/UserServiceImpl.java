package com.leepark.fli.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leepark.fli.model.dao.UserDao;
import com.leepark.fli.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;

	@Override
	@Transactional
	public Integer insert(User user) {
    	User u = userDao.select(user.getUserId(), user.getUserSns());
    	if(u == null) {
    		return userDao.insert(user);
    	} else {
    		return userDao.update(user);
    	}
	}

	@Override
	public User select(String userId, int userSns) {
		return userDao.select(userId, userSns);
	}

	@Override
	public Boolean isUsed(String userId, int userSns) {
		if(userDao.select(userId, userSns) != null) return true;
		return false;
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public void delete(String userId, int userSns) {
		userDao.delete(userId, userSns);
	}

}
