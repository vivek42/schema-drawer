package com.crawler.schema.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.dao.EventDao;
import com.crawler.schema.web.model.Event;

public class TestEventService extends Mockito {
	
	EventService objectUnderTest;
	EventDao mockEventDao;

	@Before
	public void setup() {
		mockEventDao = mock(EventDao.class);
		objectUnderTest = new EventService(mockEventDao);
	}
	
	@Test
	public void testLogEvent() {
		doNothing().when(mockEventDao).logEvent(any(Event.class));
		Event testEvent = new Event();
		
		objectUnderTest.logEvent(testEvent);
		
		verify(mockEventDao, times(1)).logEvent(testEvent);
	}

}
