package com.crawler.schema.web.model;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

public class TestUploadRow {
	
	UploadRow objectUnderTest;

	@Test
	public void testGettersAndSetters() {
		objectUnderTest = new UploadRow();
		objectUnderTest.setSerialNumber(1L);
		objectUnderTest.setFileName("fileName");
		objectUnderTest.setUploadTime(new Timestamp((new Date()).getTime()));
		
		assertEquals(new Long(1), objectUnderTest.getSerialNumber());
		assertEquals("fileName", objectUnderTest.getFileName());
		assertNotNull(objectUnderTest.getUploadTime());
	}

}
