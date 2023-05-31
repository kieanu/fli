package com.leepark.fli.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StreamUtils;

import com.leepark.fli.model.dao.MusicDao;
import com.leepark.fli.model.dto.Favorite;
import com.leepark.fli.model.dto.Like;
import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;

@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicDao musicDao;

	// 특정 노래를 뽑아옴(클라이언트가 사용할 일이 있나?)
    public Music select(Integer musicId) {
    	Music music = musicDao.select(musicId);
    	
		try {
	        Resource resource = new ClassPathResource("static/images/" + music.getMusicImg() + ".jpg");
	        InputStream inputStream = resource.getInputStream();
	        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
			String base64String = Base64Utils.encodeToString(imageBytes);
			music.setRealImg(base64String);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return music;
    }

    public List<Music> selectAll() {
    	List<Music> musics = musicDao.selectAll();
    	
    	makeImg(musics);
		
		return musics;
    }
    
	@Override
	public List<Music> selectAllByPaging(Integer lastId, Integer count) {
    	List<Music> musics = musicDao.selectAllByPaging(lastId, count);
    	
    	makeImg(musics);
    	
		return musics;
	}
	
	
    
    // 장르코드로 노래를 뽑아옴
    public List<Music> selectByGenre(Integer genreId) {
    	List<Music> musics = musicDao.selectByGenre(genreId);
    	
    	makeImg(musics);
		
		return musics;
    }
    
    // 유저 id로 유저가 좋아요한 목록을 뽑아옴
    public List<Music> selectByFavorite(String userId, Integer userSns) {
    	List<Music> musics = musicDao.selectByFavorite(userId, userSns);
    	
    	makeImg(musics);
		
		return musics;
    }

	@Override
	public List<Music> selectByPlaylist(Integer playlistId) {
		List<Music> musics = musicDao.selectByPlaylist(playlistId);
		
    	makeImg(musics);
    	
		return musics;
	}
	
	// 이미지 조립 -> " " 제거해야함
	private void makeImg(List<Music> musics) {
		int loop = musics.size();

		for (int i = 0; i < loop; i++) {
		    try {
		        Resource resource = new ClassPathResource("static/images/" + musics.get(i).getMusicImg() + ".jpg");
		        InputStream inputStream = resource.getInputStream();
		        byte[] imageBytes = StreamUtils.copyToByteArray(inputStream);
		        String base64String = Base64Utils.encodeToString(imageBytes);
		        musics.get(i).setRealImg(base64String);
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}

	@Override
	public List<Music> selectAllByRank() {
		List<Music> musics = musicDao.selectAllByRank();
		
    	makeImg(musics);
    	
		return musics;
	}

	@Override
	public List<Music> selectAllByRankByPaging(Integer lastRank, Integer count) {
		List<Music> musics = musicDao.selectAllByRankByPaging(lastRank, count);
		
    	makeImg(musics);
    	
		return musics;
	}
	
	@Override
	public List<Music> selectByRank(Integer ranking) {
		List<Music> musics = musicDao.selectByRank(ranking);
		
    	makeImg(musics);
    	
		return musics;
	}

	@Override
	public List<Music> selectAllBySearch(String keyword) {
		List<Music> musics = musicDao.selectAllBySearch(keyword);
		
    	makeImg(musics);
    	
		return musics;
	}

	@Override
	public Integer updateLike(Favorite favorite) {
		return musicDao.updateLike(favorite);
	}

}
