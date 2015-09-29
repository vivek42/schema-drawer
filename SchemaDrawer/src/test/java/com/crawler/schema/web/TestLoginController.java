package com.crawler.schema.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestLoginController extends Mockito {

	LoginController objectUnderTest;
	
	@Before
	public void setup() {
		objectUnderTest = new LoginController();
	}
	
	@Test
	public void testLogin() {
		assertEquals("login", objectUnderTest.doLogin());
	}

}
