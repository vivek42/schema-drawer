package com.crawler.schema;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.model.UploadRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public ModelAndView renderUploadPage(Locale locale, Model model){
		ModelAndView mav = new ModelAndView("upload");
		UploadRequest uploadRequest = new UploadRequest();
		((Model) mav).addAttribute("uploadRequest", uploadRequest);
		return mav;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView submitUploadContent(Locale locale, Model model){
		// TODO : add request object and service for inserting the uploaded content
		return null;
	}
	
}
