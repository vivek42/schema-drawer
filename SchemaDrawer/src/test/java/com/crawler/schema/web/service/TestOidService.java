package com.crawler.schema.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestOidService {
	
	OidService objectUnderTest;

	@Before
	public void setup() {
		objectUnderTest = new OidService();
	}
	@Test
	public void testGetOid() {
		
		assertNotNull(objectUnderTest.getOid());
		assertNotNull(objectUnderTest.getOid(123L));
	}

}
