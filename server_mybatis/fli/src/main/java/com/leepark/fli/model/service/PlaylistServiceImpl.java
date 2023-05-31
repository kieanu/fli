package com.leepark.fli.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leepark.fli.model.dao.PlaylistDao;
import com.leepark.fli.model.dto.Playlist;


@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistDao playlistDao;

	@Override
	@Transactional
	public Integer insert(Playlist playlist) {
		return playlistDao.insert(playlist);
	}
	
	@Override
	public List<Playlist> selectAll(String userId, Integer userSns) {
		return playlistDao.selectAll(userId, userSns);
	}

	@Override
	public Integer delete(Integer playlistId) {
		return playlistDao.delete(playlistId);
	}

//	@Override
//	public List<PlaylistInfo> selectAll(String userId, Integer userSns) {
//		return playlistDao.selectAll(userId, userSns);
//	}

}
