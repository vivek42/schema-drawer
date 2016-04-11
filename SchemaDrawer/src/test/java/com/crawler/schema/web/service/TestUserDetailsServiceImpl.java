package com.crawler.schema.web.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;

public class TestUserDetailsServiceImpl extends Mockito{

	UserDetailsServiceImpl objectUnderTest;
	UserService mockUserService;
	
	@Before
	public void setup() {
		mockUserService = mock(UserService.class);
		objectUnderTest = spy(new UserDetailsServiceImpl());
		when(objectUnderTest.getUserService()).thenReturn(mockUserService);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testLoadUserByUsername() {
		when(mockUserService.findUserByName("tstUsrname")).thenReturn(createTestUser());
		org.springframework.security.core.userdetails.UserDetails 
		securityUser = objectUnderTest.loadUserByUsername("tstUsrname");
		assertNotNull(securityUser);
	}
	
	private User createTestUser() {
		User testUser = new User();
		testUser.setActive(true);
		testUser.setUserId(101L);
		testUser.setPassword("tstPwd");
		testUser.setUsername("tstUsrname");
		List<Role> tstRoles = new ArrayList<Role>();
		Role tstRole = Role.ADMIN;
		tstRoles.add(tstRole);
		testUser.setRoles(tstRoles);
		return testUser;
	}
}
