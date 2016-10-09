package com.crawler.schema.web.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

public class TestUploadDao {
	
	UploadDao objectUnderTest;
	Connection mockConn;
	PreparedStatement mockPs;
	
	@Before
	public void setup() throws SQLException {
		mockConn = mock(Connection.class);
		mockPs = mock(PreparedStatement.class);
		objectUnderTest = new UploadDao(null);
		objectUnderTest = spy(objectUnderTest);
		when(objectUnderTest.getConnectionForMethod()).thenReturn(mockConn);
		when(mockConn.prepareStatement(any(String.class))).thenReturn(mockPs);
		
	}
	
	@Test
	public void testUpload() throws SQLException {
		UploadRequest request = new UploadRequest();
		request.setFileName("testFileName");
		request.setUploadId(123L);
		objectUnderTest.upload(request, null, "username", 123L);
		verify(mockPs, times(2)).executeUpdate();
	}
	
	@Test
	public void testGetUploadHistoryForUsername() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockPs.executeQuery()).thenReturn(mockRs);
		when(mockRs.next()).thenReturn(true).thenReturn(false);
		when(mockRs.getTimestamp(eq("upload_time"))).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(mockRs.getString("file_name")).thenReturn("filename");
		List<UploadRow> rows = objectUnderTest.getUploadHistoryForUsername("username");
		assertEquals(1, rows.size());
		assertEquals("filename", rows.get(0).getFileName());
	}
	
	@Test
	public void testGetDownloadStreamForFile() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockPs.executeQuery()).thenReturn(mockRs);
		when(mockRs.next()).thenReturn(true).thenReturn(false);
		UploadRow row = new UploadRow();
		row.setFileName("filename");
		row.setUploadTime(new Timestamp(System.currentTimeMillis()));
		when(mockRs.getBytes(eq("content"))).thenReturn("123".getBytes());
		assertNotNull(objectUnderTest.getDownloadStreamForFile(row, "username"));
		verify(mockRs, times(1)).getBytes(any(String.class));
		
		when(mockRs.getBytes(eq("content"))).thenReturn(null);
		assertNotNull(objectUnderTest.getDownloadStreamForFile(row, "username"));
		verify(mockRs, times(1)).getBytes(any(String.class));
		
	}
	
	@Test
	public void testCoverExceptions() throws SQLException {
		when(mockConn.prepareStatement(any(String.class))).thenThrow(new SQLException());
		try {
			objectUnderTest.upload(null, null, null, null);
			objectUnderTest.getUploadHistoryForUsername(null);
			objectUnderTest.getDownloadStreamForFile(null, null);
		} catch(Exception e) {
			fail("Exception is not expected");
		}
		
		
	}

}
