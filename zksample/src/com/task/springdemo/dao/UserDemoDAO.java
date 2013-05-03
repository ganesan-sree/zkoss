package com.task.springdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.task.springdemo.bean.User;



/**
 * @author zkessentials store
 * 
 *         This class provides functionality to access the {@code User} model
 *         storage system
 * 
 */
@Repository
//@Scope(value="request", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserDemoDAO{
	
	@PersistenceContext
	EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
        Query query = em.createQuery("from users");
        List<User> users = query.getResultList();
        return users;
	}

	public User findById(long id) {
		return Querys.findSingle(User.class, "id", id, em);
	}

	public User findUserByName(String name) {
		System.out.println(name);
        return Querys.findSingle(User.class, "name", name, em);
	}


}
