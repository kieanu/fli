package com.leepark.fli.model.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leepark.fli.model.dao.FavoriteDao;
import com.leepark.fli.model.dao.UserDao;
import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.User;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    
    @Autowired
    private FavoriteDao favoriteDao;

	@Override
	public List<Favorite> selectAll(String userId, Integer userSns) {
		return favoriteDao.selectAll(userId, userSns);
	}

	@Override
	@Transactional
	public int insert(Favorite favorite) {
		return favoriteDao.insert(favorite);
	}

	@Override
	public int delete(Favorite favorite) {
		return favoriteDao.delete(favorite);
	}

	@Override
	public Boolean isFavorite(String userId, Integer userSns, Integer musicId) {
		if(favoriteDao.select(userId, userSns, musicId) != null) return true;
		return false;
	}
}
