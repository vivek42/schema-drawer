package com.crawler.schema.web.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;

public interface UserService {

	void addUser(User user);
	User findUserByName(String username);
	void addUserProfile(UserProfile userProfile, List<Role> roles)
			throws NoSuchAlgorithmException;
}
