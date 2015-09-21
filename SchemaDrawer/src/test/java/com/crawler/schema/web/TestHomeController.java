package com.crawler.schema.web;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;

public class TestHomeController {
	
	HomeController objectUnderTest;
	UploadService mockUploadService;
	EventService mockEventService;
	OidService mockOidService;

	@Before
	public void setup() {
		mockUploadService = Mockito.mock(UploadService.class);
		mockEventService = Mockito.mock(EventService.class);
		mockOidService = Mockito.mock(OidService.class);
		objectUnderTest = new HomeController(mockUploadService, mockEventService, mockOidService);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_redirectHome() {
		ModelAndView answer = objectUnderTest.redirectHome(null, null);
		assertEquals("redirect:/admin/upload", answer.getViewName());
	}
	
	public void test_home() {
		Model testModel = new Model() {
			
			@Override
			public Model mergeAttributes(Map<String, ?> attributes) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsAttribute(String attributeName) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Model addAttribute(String attributeName, Object attributeValue) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Model addAttribute(Object attributeValue) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Model addAllAttributes(Map<String, ?> attributes) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Model addAllAttributes(Collection<?> attributeValues) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
	}

}
