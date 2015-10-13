package com.crawler.schema.web.service;

import com.crawler.schema.web.model.User;

public interface UserService {

	User addUser(User user);
	User findUserByUsername(String username);
	void updatePassword(User user);
}
