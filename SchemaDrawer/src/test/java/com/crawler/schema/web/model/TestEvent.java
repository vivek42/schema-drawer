package com.crawler.schema.web.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEvent {
	
	Event objectUnderTest;
	
	@Before
	public void setup() {
		objectUnderTest = new Event();
	}
	
	@After
	public void tearDown() {
		
	}

	@Test
	public void testGettersAndSetters() {
		objectUnderTest.setEventId(101L);
		objectUnderTest.setEventCode("event code");
		objectUnderTest.setEventTime(new Date());
		objectUnderTest.setMessage("message");
		objectUnderTest.setStackTrack("stackTrace");
		objectUnderTest.setApplicationName("applicationname");
		assertEquals(new Long(101), objectUnderTest.getEventId());
		assertEquals("event code", objectUnderTest.getEventCode());
		assertNotNull(objectUnderTest.getEventTime());
		assertEquals("message", objectUnderTest.getMessage());
		assertEquals("stackTrace", objectUnderTest.getStackTrack());
		assertEquals("applicationname", objectUnderTest.getApplicationName());
	}

}
