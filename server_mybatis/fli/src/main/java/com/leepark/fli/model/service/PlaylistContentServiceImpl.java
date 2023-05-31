package com.leepark.fli.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leepark.fli.model.dao.PlaylistContentDao;
import com.leepark.fli.model.dao.PlaylistDao;
import com.leepark.fli.model.dto.PlaylistContent;


@Service
public class PlaylistContentServiceImpl implements PlaylistContentService {
    @Autowired
    private PlaylistContentDao playlistContentDao;

	@Override
	@Transactional
	public Integer insertMusic(PlaylistContent playlistContent) {
		return playlistContentDao.insertMusic(playlistContent);
	}

	@Override
	public Integer deleteMusic(PlaylistContent playlistContent) {
		return playlistContentDao.deleteMusic(playlistContent);
	}

	@Override
	public Integer deleteAll(Integer playlistId) {
		return playlistContentDao.deleteAll(playlistId);
	}

}
