package com.crawler.schema.web.service.impl;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(UserDao dao){
		this.setUserDao(dao);
	}

	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
