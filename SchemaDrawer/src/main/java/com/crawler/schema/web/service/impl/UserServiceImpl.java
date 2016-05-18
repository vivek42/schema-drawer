package com.crawler.schema.web.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	private OidService oidService;
	
	public UserServiceImpl(UserDao dao, OidService oidService){
		this.userDao = dao;
		this.oidService = oidService;
	}
	
	@Override
	public void addUserProfile(UserProfile userProfile, List<Role> roles) throws NoSuchAlgorithmException{
		User user = new User();
		Long userId = oidService.getOid();
		user.setUserId(userId);
		user.setActive(true);
		user.setRoles(roles);
		user.setUsername(userProfile.getUsername());
		user.setPassword(encryptPassword(userProfile.getPassword()));
		userDao.addUser(user);
		userProfile.setId(userId);
		userDao.persistUserProfile(userProfile);
	}

	@Override
	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	protected String encryptPassword(String input) throws NoSuchAlgorithmException{
		// TODO: Message digest is not thread safe, make it thread safe
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        
        byte byteData[] = md.digest();
        
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
	}

	@Override
	public UserProfile getUserProfileByName(String username) {
		return userDao.getUserProfileByName(username);
	}

	@Override
	public void updateUserProfile(UserProfile profile) {
		userDao.updateUserProfile(profile);
	}

}
