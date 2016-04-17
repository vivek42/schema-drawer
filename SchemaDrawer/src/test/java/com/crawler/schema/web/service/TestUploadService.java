package com.crawler.schema.web.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

public class TestUploadService extends Mockito{
	
	UploadService objectUnderTest;
	UploadDao mockUploadDao;

	@Before
	public void setup() {
		mockUploadDao = mock(UploadDao.class);
		objectUnderTest = new UploadService(mockUploadDao);
	}
	
	@Test
	public void testUpload() {
		UploadRequest testUploadRequest = new UploadRequest();
		doNothing().when(mockUploadDao).upload(any(UploadRequest.class),  any(byte[].class), any(String.class), any(Long.class));
		objectUnderTest.upload(testUploadRequest, "test".getBytes(), "testUsername", new Long(123));
		verify(mockUploadDao, times(1)).upload(any(UploadRequest.class),  any(byte[].class), any(String.class), any(Long.class));;
	}
	
	@Test
	public void testGetUploadHistoryForUsername() {
		List<UploadRow> testResult = new ArrayList<UploadRow>();
		when(mockUploadDao.getUploadHistoryForUsername(eq("testUsername"))).thenReturn(testResult);
		objectUnderTest.getUploadHistoryForUsername("testUsername");
		verify(mockUploadDao, times(1)).getUploadHistoryForUsername(eq("testUsername"));
	}

}
