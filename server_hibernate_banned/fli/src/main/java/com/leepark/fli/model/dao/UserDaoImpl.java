package com.leepark.fli.model.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.leepark.fli.model.dto.User;
import com.leepark.fli.model.dtoId.UserId;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	EntityManager entityManager;
	
//	@Autowired
//    private EntityManagerFactory emf;
//
//    EntityManager entityManager = emf.createEntityManager();

    public User select(UserId userId) {
    	return entityManager.find(User.class, userId);
    }

    public List<User> selectAll() {
    	return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
    
    @Transactional
    // 플레이리스트 정보가 추가 될 때 User 정보가 추가로 생성됨
    public Boolean insert(User user) {
    	try {
//    	    entityManager.getTransaction().begin(); // 트랜잭션 시작
    	    entityManager.persist(user); // 엔티티 영속화 (데이터베이스에 삽입)
//    	    entityManager.getTransaction().commit(); // 트랜잭션 커밋
    	} catch (PersistenceException e) {
//    	    entityManager.getTransaction().rollback(); // 트랜잭션 롤백
    	    // 실패한 경우에 대한 처리 로직을 추가로 구현
    	    System.out.println("DB 작업 실패: " + e.getMessage());
    	    return false;
    	}
    	return true;
    }
    
    // 플레이리스트 정보가 삭제 될 때 User정보가 삭제됨
    public Boolean delete(UserId userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
        	try {
        	    entityManager.getTransaction().begin(); // 트랜잭션 시작
                entityManager.remove(user);
        	    entityManager.getTransaction().commit(); // 트랜잭션 커밋
        	} catch (PersistenceException e) {
        	    entityManager.getTransaction().rollback(); // 트랜잭션 롤백
        	    // 실패한 경우에 대한 처리 로직을 추가로 구현
        	    System.out.println("DB 작업 실패: " + e.getMessage());
        	    return false;
        	}
        }
        return true;
    }
    
}
