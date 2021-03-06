package com.crawler.schema.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crawler.schema.web.model.Event;
import com.crawler.schema.web.model.Role;
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
	
	private static Logger LOGGER = Logger.getLogger(ProfileController.class);
	
	
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
	
	@RequestMapping(value = "/profile/create", method=RequestMethod.POST)
	public ModelAndView submitProfilePage(@ModelAttribute UserProfile profile, BindingResult result, RedirectAttributes redirectAttrs) {
		String message = "";
		try {
			//TODO : save the user do the hex encoding of the password and redirect to login page with a message
			userService.addUserProfile(profile, getDefaultRoles());
			message="User profile successfully created! Please login to continue!";
		}catch (Exception e) {
			Event event = new Event(this.getClass().getName() + ".submitProfilePage",e);
			eventService.logEvent(event);
			message = "we have encountered an error";
		}
		LOGGER.info(message);
		// TODO : add request object and service for inserting the uploaded content
		redirectAttrs.addFlashAttribute("message", message);
		return new ModelAndView("redirect:/home");
	}
	
	private List<Role> getDefaultRoles() {
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.ADMIN);
		return roles;
	}
	
	@RequestMapping(value = "/profile/edit", method=RequestMethod.GET)
	public ModelAndView getProfilePage(Model model, Principal principal) {
		UserProfile profile = userService.getUserProfileByName(principal.getName());
		model.addAttribute("profile", (profile==null ? new UserProfile() : profile));
		return new ModelAndView("profile");
	}
	
	@RequestMapping(value="/profile/edit", method=RequestMethod.POST)
	public ModelAndView updateProfilePage(@ModelAttribute UserProfile profile, BindingResult result) {
		userService.updateUserProfile(profile);
		return new ModelAndView("redirect:/profile/edit");
	}
}
