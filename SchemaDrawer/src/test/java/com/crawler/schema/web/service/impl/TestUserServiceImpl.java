package com.crawler.schema.web.service.impl;

import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;
import com.crawler.schema.web.service.OidService;

public class TestUserServiceImpl extends Mockito{

	UserDao mockDao;
	OidService mockOidService;
	UserServiceImpl objectUnderTest;
	
	@Before
	public void setup() {
		mockDao = mock(UserDao.class);
		mockOidService = mock(OidService.class);
		objectUnderTest = new UserServiceImpl(mockDao, mockOidService);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testAddUserProfile() throws NoSuchAlgorithmException {
		UserProfile profile = new UserProfile();
		profile.setUsername("tstname");
		profile.setPassword("tstpwd");
		when(mockOidService.getOid()).thenReturn(101L);
		List<Role> tstRoles = new ArrayList<Role>();
		tstRoles.add(Role.ADMIN);
		objectUnderTest.addUserProfile(profile, tstRoles);
		
		verify(mockDao, times(1)).addUser(any(User.class));
		verify(mockDao, times(1)).persistUserProfile(profile);
	}
	
	@Test
	public void testFindUserByName() {
		User tstUser = new User();
		when(mockDao.findUserByName("testName")).thenReturn(tstUser);
		assertEquals(tstUser, objectUnderTest.findUserByName("testName"));
	}
	
	@Test
	public void testGettersAndSetters() {
		objectUnderTest.setUserDao(null);
		assertNull(objectUnderTest.getUserDao());
	}
	
	@Test
	public void testEncryptedPassword() throws NoSuchAlgorithmException {
		assertEquals("a29bac723ca2d59ed78a2d715e17e92f",
				objectUnderTest.encryptPassword("sai"));
	}
	
	@Test
	public void testGetUserProfileByName() {
		UserProfile profile = new UserProfile();
		when(mockDao.getUserProfileByName(eq("test"))).thenReturn(profile);
		assertEquals(profile, objectUnderTest.getUserProfileByName("test"));
	}
	
	@Test
	public void testUpdateUserProfile() {
		UserProfile profile = new UserProfile();
		objectUnderTest.updateUserProfile(profile);
		verify(mockDao, times(1)).updateUserProfile(eq(profile));
	}

}
