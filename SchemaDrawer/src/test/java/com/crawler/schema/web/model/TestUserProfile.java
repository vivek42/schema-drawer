package com.crawler.schema.web.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class TestUserProfile {
	
	UserProfile objectUnderTest;

	@Test
	public void testGettersAndSetters() {
		objectUnderTest = new UserProfile();
		objectUnderTest.setDob(new Date());
		objectUnderTest.setEmailAddress("emailAddress");
		objectUnderTest.setFirstName("firstName");
		objectUnderTest.setGender("male");
		objectUnderTest.setId(101L);
		objectUnderTest.setLastName("lastName");
		objectUnderTest.setPassword("password");
		objectUnderTest.setUsername("username");
		assertEquals("emailAddress", objectUnderTest.getEmailAddress());
		assertEquals("firstName", objectUnderTest.getFirstName());
		assertEquals("male", objectUnderTest.getGender());
		assertEquals("lastName", objectUnderTest.getLastName());
		assertEquals("password", objectUnderTest.getPassword());
		assertEquals("username", objectUnderTest.getUsername());
	}

}
