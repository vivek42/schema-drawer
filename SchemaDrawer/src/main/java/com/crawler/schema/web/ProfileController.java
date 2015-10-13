package com.crawler.schema.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.model.User;
import com.crawler.schema.web.service.UserService;

@Controller
@SessionAttributes("user")
public class ProfileController {

	UserService userService;
	
	@Autowired
	public ProfileController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/profile/changePassword", method = RequestMethod.POST)
	public ModelAndView changePassword(@ModelAttribute User user) {
		userService.updatePassword(user);
		return new ModelAndView("redirect:/login");
	}
	
}
