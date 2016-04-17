package com.crawler.schema.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.model.Event;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;

public class TestHomeController extends Mockito {
	
	HomeController objectUnderTest;
	UploadService mockUploadService;
	EventService mockEventService;
	OidService mockOidService;
	MultipartFile mockFile; 

	@Before
	public void setup() {
		mockUploadService = mock(UploadService.class);
		mockEventService = mock(EventService.class);
		mockOidService = mock(OidService.class);
		mockFile = mock(MultipartFile.class);
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
		Principal testp = mock(Principal.class);
		when(testp.getName()).thenReturn("testUsername");
		when(mockUploadService.getUploadHistoryForUsername(eq("testUsername"))).thenReturn(null);
		ModelAndView answer = objectUnderTest.home(testModel, testp);
		verify(testModel, times(2)).addAttribute(any(String.class), any(Object.class));
		assertEquals("home", answer.getViewName());
	}
	
	@Test
	public void testSubmitUploadContent_happyPath() throws IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		UploadRequest mockUploadRequest = mock(UploadRequest.class);
		Principal principal = mock(Principal.class);
		when(principal.getName()).thenReturn("testUsername");
		when(mockOidService.getOid()).thenReturn(123L);
		doNothing().when(mockUploadRequest).setUploadId(any(Long.class));
		doNothing().when(mockUploadRequest).setUploadTime(any(Date.class));
		when(mockUploadRequest.getUploadContentFile()).thenReturn(mockFile);
		when(mockFile.getBytes()).thenReturn(new byte[5]);
		doNothing().when(mockUploadService).upload(any(UploadRequest.class), any(byte[].class), any(String.class), any(Long.class));
		doNothing().when(mockRequest).setAttribute(any(String.class), any(String.class));
		ModelAndView answer = objectUnderTest.submitUploadContent(mockUploadRequest, mockRequest, null);
		
		assertEquals("redirect:/admin/upload", answer.getViewName());
	}
	
	@Test
	public void testSubmitUploadContent_Exception() throws IOException {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		UploadRequest mockUploadRequest = mock(UploadRequest.class);
		when(mockOidService.getOid()).thenThrow(new NullPointerException());
		ModelAndView answer = objectUnderTest.submitUploadContent(mockUploadRequest, mockRequest, null);
		verify(mockEventService, times(1)).logEvent(any(Event.class));
		assertEquals("redirect:/admin/upload", answer.getViewName());
	}
}
