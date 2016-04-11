package com.crawler.schema.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;

public class TestHomeController extends Mockito {
	
	HomeController objectUnderTest;
	UploadService mockUploadService;
	EventService mockEventService;
	OidService mockOidService;

	@Before
	public void setup() {
		mockUploadService = mock(UploadService.class);
		mockEventService = mock(EventService.class);
		mockOidService = mock(OidService.class);
		objectUnderTest = new HomeController(mockUploadService, mockEventService, mockOidService);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testRedirectHome() {
		ModelAndView answer = objectUnderTest.redirectHome(null, null);
		assertEquals("redirect:/admin/upload", answer.getViewName());
	}
	
	@Test
	public void testHome() {
		Model testModel = mock(Model.class);
		ModelAndView answer = objectUnderTest.home(testModel);
		verify(testModel, times(1)).addAttribute(any(String.class), any(Object.class));
		assertEquals("home", answer.getViewName());
	}
	
	@Test
	public void testSubmitUploadContent_happyPath() throws IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		UploadRequest mockUploadRequest = mock(UploadRequest.class);
		when(mockOidService.getOid()).thenReturn(123L);
		doNothing().when(mockUploadRequest).setUploadId(any(Long.class));
		doNothing().when(mockUploadRequest).setUploadTime(any(Date.class));
		when(mockUploadRequest.getUploadContent()).thenReturn("testContent");
		doNothing().when(mockUploadService).upload(any(UploadRequest.class), any(byte[].class));
		doNothing().when(mockRequest).setAttribute(any(String.class), any(String.class));
		ModelAndView answer = objectUnderTest.submitUploadContent(mockUploadRequest, mockRequest);
		
		assertEquals("message", answer.getViewName());
	}
}
