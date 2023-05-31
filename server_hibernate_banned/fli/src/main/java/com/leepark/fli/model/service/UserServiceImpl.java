package com.leepark.fli.model.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leepark.fli.model.dao.UserDao;
import com.leepark.fli.model.dao.UserDaoImpl;
import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.dtoId.UserId;

@Service
public class UserServiceImpl implements UserService {
    // 싱글톤
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        return instance;
    }
    
    @Autowired
    private UserDao userDao;

	@Override
	public Boolean insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User select(UserId userId) {
		return userDao.select(userId);
	}

	@Override
	public Boolean isUsed(UserId userId) {
		if(userDao.select(userId) != null) return true;
		return false;
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public Boolean delete(UserId userId) {
		return userDao.delete(userId);
	}

}
