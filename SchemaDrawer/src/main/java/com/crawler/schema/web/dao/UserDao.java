package com.crawler.schema.web.dao;

import com.crawler.schema.web.model.User;

public interface UserDao {
	User addUser(User user);
	User findUserByUsername(String username);
	void updatePassword(User user);
}
