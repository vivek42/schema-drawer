package com.crawler.schema.web.config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestAppConfig {

	AppConfig objectUnderTest;
	
	@Before
	public void setup() {
		objectUnderTest = new AppConfig();
	}
	
	@After
	public void tearDown() {
		objectUnderTest = null;
	}
	
	@Test
	public void testAllGetters() {
		assertNotNull(objectUnderTest.getUploadService());
		assertNotNull(objectUnderTest.getUserService());
		assertNotNull(objectUnderTest.getEventService());
		assertNotNull(objectUnderTest.getOidService());
		assertNotNull(objectUnderTest.getConnection());
		assertNotNull(objectUnderTest.getCommonsMultipartResolver());
	}

}
