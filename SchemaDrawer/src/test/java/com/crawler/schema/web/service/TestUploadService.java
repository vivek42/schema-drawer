package com.crawler.schema.web.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.model.UploadRequest;

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
		doNothing().when(mockUploadDao).upload(any(UploadRequest.class),  any(byte[].class));
		objectUnderTest.upload(testUploadRequest, "test".getBytes());
		verify(mockUploadDao, times(1)).upload(any(UploadRequest.class),  any(byte[].class));;
	}

}
