package com.leepark.fli.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.leepark.fli.model.dto.Music;
import com.leepark.fli.model.dto.User;


@Repository
public class MusicDaoImpl implements MusicDao {
    @PersistenceContext
    private EntityManager entityManager;
    
	@Override
	public Music select(Integer musicId) {
		return entityManager.find(Music.class, musicId);
	}

	@Override
	public List<Music> selectAll() {
		return entityManager.createQuery("SELECT m FROM Music m", Music.class).getResultList();
	}

	@Override
	public List<Music> selectByGenre(Integer genreId) {
		// : 은 setparameter에 넣어준다
		String hql = "SELECT m FROM Music m WHERE m.genres.genre_id = :genreId";
		Query query = entityManager.createQuery(hql).setParameter("genreId", genreId);
		List<Music> musics = query.getResultList();
		
		return musics;
	}

	@Override
	public List<Music> selectByFavorite(User user) {
		String jpql = "SELECT f FROM Favorite f JOIN User u WHERE f.user_id = u.user_id AND f.user_sns = u.user_sns";
		Query query = entityManager.createQuery(jpql);
		
		List<Music> musics = query.getResultList();

		return musics;
	}

}
