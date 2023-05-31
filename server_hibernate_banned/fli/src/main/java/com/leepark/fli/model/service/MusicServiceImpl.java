package com.leepark.fli.model.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.leepark.fli.model.dao.MusicDao;
import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;

@Service
public class MusicServiceImpl implements MusicService {
    // 싱글톤
    private static MusicServiceImpl instance = new MusicServiceImpl();

    private MusicServiceImpl() {}

    public static MusicServiceImpl getInstance() {
        return instance;
    }
    
    @Autowired
    private MusicDao musicDao;

	// 특정 노래를 뽑아옴(클라이언트가 사용할 일이 있나?)
    public Music select(Integer musicId) {
    	Music music = musicDao.select(musicId);
		
		ClassPathResource resource = new ClassPathResource("/static/images/" + music.getMusicImg() + ".jpg");
		try {
			byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
			music.setRealImg(imageBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return music;
    }

    public List<Music> selectAll() {
    	List<Music> musics = musicDao.selectAll();
    	
		// 이미지 조립
		int loop = musics.size();
		
		for(int i=0;i<loop;i++) {
			ClassPathResource resource = new ClassPathResource("/static/images/" + musics.get(i).getMusicImg() + ".jpg");
			try {
				byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
				musics.get(i).setRealImg(imageBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return musics;
    }
    
    // 장르코드로 노래를 뽑아옴
    public List<Music> selectByGenre(Integer genreId) {
    	List<Music> musics = musicDao.selectByGenre(genreId);
    	
		// 이미지 조립
		int loop = musics.size();
		
		for(int i=0;i<loop;i++) {
			ClassPathResource resource = new ClassPathResource("/static/images/" + musics.get(i).getMusicImg() + ".jpg");
			try {
				byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
				musics.get(i).setRealImg(imageBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return musics;
    }
    
    // 유저 id로 유저가 좋아요한 목록을 뽑아옴
    public List<Music> selectByFavorite(User user) {
    	List<Music> musics = musicDao.selectByFavorite(user);
    	
		// 이미지 조립
		int loop = musics.size();
		
		for(int i=0;i<loop;i++) {
			ClassPathResource resource = new ClassPathResource("/static/images/" + musics.get(i).getMusicImg() + ".jpg");
			try {
				byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());
				musics.get(i).setRealImg(imageBytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return musics;
    }

}
