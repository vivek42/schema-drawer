package com.crawler.schema.web;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.model.Event;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;
import com.sun.net.httpserver.HttpPrincipal;

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
		verify(testModel, times(3)).addAttribute(any(String.class), any(Object.class));
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
	
	@Test
	public void testFileDownload_happyPath() throws IOException {
		File tmpFile = File.createTempFile("test", "");
		FileWriter writer = new FileWriter(tmpFile);
		writer.write("test file content");
		writer.close();
		InputStream in = new FileInputStream(tmpFile);
		UploadRow row = new UploadRow();
		row.setFileName("testFilename");
		Principal user = mock(Principal.class);
		when(user.getName()).thenReturn("testUsername");
		when(mockUploadService.getDownloadStreamForFile(any(UploadRow.class), any(String.class))).thenReturn(in);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		
		objectUnderTest.fileDownload(row, mockRequest, mockResponse, user);
		
		verify(mockResponse, times(1)).setContentType("application/octet-stream");
		verify(mockResponse, times(1)).setHeader("Content-Disposition","attachment;filename=" + row.getFileName());
		verify(mockResponse, times(1)).getOutputStream();
		
	}
	
	@Test
	public void testDiagramDownload() throws IOException {
		objectUnderTest = spy(objectUnderTest);

		InputStream in = mock(InputStream.class);
		UploadRow row = new UploadRow();
		row.setFileName("testFilename");
		Principal user = mock(Principal.class);
		when(user.getName()).thenReturn("testUsername");
		when(mockUploadService.getDownloadStreamForDiagram(eq(row), eq("testUsername"))).thenReturn(in);
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		doNothing().when(objectUnderTest).inputStreamToDownload(any(String.class), any(InputStream.class), eq(mockResponse), any(String.class));		
		
		//doNothing().when(objectUnderTest).inputStreamToDownload(eq("testFilename"), eq(in), eq(mockResponse), eq("_output.html"));		
		objectUnderTest.diagramDownload(row, mockRequest, mockResponse, user);
		
		verify(objectUnderTest, times(1)).inputStreamToDownload(eq("testFilename"), eq(in), eq(mockResponse), eq("_output.html"));
		verify(mockUploadService, times(1)).getDownloadStreamForDiagram(eq(row), eq("testUsername"));
	}
	
	@Test
	public void testGuestHome() {
		Model mockModel = mock(Model.class);
		Principal mockPrincipal = mock(Principal.class);
		
		ModelAndView resultView = objectUnderTest.guestHome(mockModel, null);
		assertEquals("guest", resultView.getViewName());
		
		resultView = objectUnderTest.guestHome(mockModel, mockPrincipal);
		assertEquals("redirect:/admin/upload", resultView.getViewName());
	}
	
	@Test
	public void testGuestDiagramDowload() throws IllegalStateException, IOException {
		MultipartFile mockFile = mock(MultipartFile.class);
		doNothing().when(mockFile).transferTo(any(File.class));
		when(mockFile.getOriginalFilename()).thenReturn("testFileName");
		File tmpFile = File.createTempFile("test", "");
		FileWriter writer = new FileWriter(tmpFile);
		writer.write("test file content");
		writer.close();
		UploadRequest request = new UploadRequest();
		request.setUploadContentFile(mockFile);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		
		objectUnderTest.guestDiagramDownload(request, null, mockResponse, null);
		verify(mockResponse, times(1)).setContentType("application/octet-stream");
		verify(mockResponse, times(1)).setHeader("Content-Disposition","attachment;filename=testFileName_output.html");
		verify(mockResponse, times(1)).getOutputStream();
	
	}
}
