package com.crawler.schema.web.service;

import com.crawler.schema.web.model.User;

public interface UserService {

	void addUser(User user);
	User findUserByName(String username);
}
