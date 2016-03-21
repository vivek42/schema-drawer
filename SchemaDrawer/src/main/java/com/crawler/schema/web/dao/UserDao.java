package com.crawler.schema.web.dao;

import java.util.List;

import com.crawler.schema.web.model.User;

public interface UserDao {
	void addUser(User user);
	void editUser(User user);
	void deleteUser(User user);
	User findUser(int userId);
	User findUserByName(String username);
	List<User> getAllUsers();
}
