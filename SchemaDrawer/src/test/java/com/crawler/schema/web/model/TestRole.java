package com.crawler.schema.web.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRole {
	
	Role objectUnderTest;

	@Test
	public void testGetRoleByName() {
		assertEquals(Role.ADMIN, Role.getRoleByName("admin"));
	}

}
