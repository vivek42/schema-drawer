package com.crawler.schema.web;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crawler.schema.web.model.UserProfile;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UserService;

public class TestProfileController extends Mockito{
	
	ProfileController objectUnderTest;
	UserService mockUserService;
	EventService mockEventService;
	OidService mockOidService;

	@Before
	public void setup() {
		mockUserService = mock(UserService.class);
		mockEventService = mock(EventService.class);
		mockOidService = mock(OidService.class);
		objectUnderTest = new ProfileController(mockUserService, mockEventService, mockOidService);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetProfilePage() {
		Model model = mock(Model.class);
		ModelAndView answer = objectUnderTest.getProfilePage(model);
		assertEquals("signup", answer.getViewName());
	}
	
	@Test
	public void testSubmitProfile() {
		UserProfile profile = new UserProfile();
		RedirectAttributes attr = mock(RedirectAttributes.class);
		ModelAndView answer = objectUnderTest.submitProfilePage(profile, null, attr);
		verify(attr, times(1)).addFlashAttribute(any(String.class), any(String.class));
		assertEquals("redirect:/home", answer.getViewName());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSubmitProfile_Exception() throws NoSuchAlgorithmException {
		UserProfile profile = new UserProfile();
		RedirectAttributes attr = mock(RedirectAttributes.class);
		doThrow(new NoSuchAlgorithmException()).when(mockUserService).addUserProfile(eq(profile), any(List.class));
		ModelAndView answer = objectUnderTest.submitProfilePage(profile, null, attr);
		verify(attr, times(1)).addFlashAttribute(any(String.class), any(String.class));
		assertEquals("redirect:/home", answer.getViewName());
	}
}
