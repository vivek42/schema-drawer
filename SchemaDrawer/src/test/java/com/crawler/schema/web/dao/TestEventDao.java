package com.crawler.schema.web.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.crawler.schema.web.dao.impl.UserDaoImpl;
import com.crawler.schema.web.model.Event;

public class TestEventDao {
	
	EventDao objectUnderTest;
	Connection mockConnection;
	PreparedStatement mockPreparedStatement;
	
	
	@Before
	public void setup() throws SQLException {
		mockConnection = mock(Connection.class);
		mockPreparedStatement = mock(PreparedStatement.class);
		when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
		objectUnderTest = new EventDao(mockConnection);
		objectUnderTest = spy(objectUnderTest);
		when(objectUnderTest.getConnectionForMethod()).thenReturn(mockConnection);
	}

	@Test
	public void testLogEvent() throws SQLException {
		Event event = new Event();
		event.setEventId(123L);
		event.setEventCode("testEventCode");
		event.setEventTime(new Date(System.currentTimeMillis()));
		objectUnderTest.logEvent(event);
		verify(mockPreparedStatement, times(1)).executeUpdate();
		
		when(mockConnection.prepareStatement(any(String.class))).thenThrow(new SQLException());
		try {
			objectUnderTest.logEvent(null);
		} catch(Exception e) {
			fail("not expected to throw any exception");
		}
		
	}

}
