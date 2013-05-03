package com.task.springdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.task.springdemo.bean.User;
import com.task.springdemo.dao.UserDemoDAO;



/**
 * @author Ian YT Tsai(zanyking)
 * 
 *         This class provides a class which manages user authentication
 * 
 */
@Component
@Scope("session")
public class UserCredentialManager{

	private static final long serialVersionUID = 4789033910089502945L;

	private User user;

	@Autowired
	private UserDemoDAO userDao;
	
	public synchronized void login(String name, String password) {
		User tempUser = userDao.findUserByName(name);
		if (tempUser != null && tempUser.getPassword().equals(password)) {
			user = tempUser;
		} else {
			user = null;
		}
	}

	public synchronized void logOff() {
		this.user = null;
	}

	public synchronized User getUser() {
		return user;
	}

	public synchronized boolean isAuthenticated() {
		return user != null;
	}

}
