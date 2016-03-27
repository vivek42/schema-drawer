package com.crawler.schema.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.model.Event;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UserService;

@Controller
@SessionAttributes("user")
public class ProfileController {

	UserService userService;
	protected EventService eventService;
	protected OidService oidService;
	
	@Autowired
	public ProfileController(UserService userService, EventService eventService, OidService oidService) {
		this.userService = userService;
		this.eventService = eventService;
		this.oidService = oidService;
	}
	
	@RequestMapping(value = "/profile/create", method=RequestMethod.GET)
	public ModelAndView getProfilePage(Model model) {
		UserProfile profile = new UserProfile();
		model.addAttribute("profile", profile);
		return new ModelAndView("signup");
	}
	
	@RequestMapping(value = "/profile/create", method=RequestMethod.GET)
	public ModelAndView submitProfilePage(@ModelAttribute UserProfile profile, HttpServletRequest request) {
		String message = "";
		try {
			//TODO : save the user and redirect to login page with a message
		}catch (Exception e) {
			Event event = new Event();
			event.setEventId(oidService.getOid());
			event.setEventCode(this.getClass().getName() + ".submitUploadContect");
			event.setMessage(e.getMessage());
			event.setStackTrack(e.getStackTrace().toString());
			eventService.logEvent(event);
			message = "we have encountered an error";
		}
		// TODO : add request object and service for inserting the uploaded content
		request.setAttribute("message", message);
		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/profile/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute User user) {
		//userService.updatePassword(user);
		return new ModelAndView("redirect:/login");
	}
	
}
