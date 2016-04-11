package com.crawler.schema.web.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;

public class TestUploadRequest extends Mockito{
	
	UploadRequest objectUnderTest;

	@Test
	public void testGettersAndSetters() {
		objectUnderTest = new UploadRequest();
		objectUnderTest.setFileName("filename");
		objectUnderTest.setUploadContentFile(null);
		objectUnderTest.setUploadId(101l);
		objectUnderTest.setUploadTime(new Date());
		assertEquals("filename", objectUnderTest.getFileName());
		assertEquals(new Long(101), objectUnderTest.getUploadId());
		assertNotNull(objectUnderTest.getUploadTime());
		assertNull(objectUnderTest.getUploadContentFile());
	}

}
