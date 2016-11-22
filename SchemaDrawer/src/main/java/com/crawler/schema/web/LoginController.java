package com.crawler.schema.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String doLogin() {
		return "login";
	}
	
//	@RequestMapping("/")
//	public String redirectToLogin() {
//		return doLogin();
//	}
}
