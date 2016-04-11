package com.crawler.schema.web.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestUser {
	
	User objectUnderTest;

	@Test
	public void testGettersAndSetters() {
		User user = new User();
		List<Role> roles = new ArrayList<Role>();
		user.setUserId(101L);
		user.setUsername("username");
		user.setPassword("password");
		user.setActive(true);
		user.setRoles(roles);
		assertEquals(101L, user.getUserId());
		assertEquals("username", user.getUsername());
		assertEquals("password", user.getPassword());
		assertTrue(user.isActive());
		assertEquals(roles, user.getRoles());
	}

}
