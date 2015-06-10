package com.crawler.schema;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.model.Event;
import com.crawler.schema.model.UploadRequest;
import com.crawler.schema.service.EventService;
import com.crawler.schema.service.OidService;
import com.crawler.schema.service.UploadService;

@Controller
@SessionAttributes("uploadRequest")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	protected UploadService uploadService;
	protected EventService eventService;
	protected OidService oidService;
	
	@Autowired
	public HomeController(UploadService uploadService, EventService eventService, OidService oidService) {
		this.uploadService = uploadService;
		this.eventService = eventService;
		this.oidService = oidService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.addAttribute("uploadRequest", new UploadRequest());
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	@RequestMapping(value = "/upload", method = RequestMethod.GET)
//	public ModelAndView renderUploadPage(Locale locale, Model model){
//		ModelAndView mav = new ModelAndView("upload");
//		UploadRequest uploadRequest = new UploadRequest();
//		((Model) mav).addAttribute("uploadRequest", uploadRequest);
//		return mav;
//	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView submitUploadContent(@ModelAttribute UploadRequest uploadRequest, HttpServletRequest request){
		String message;
		try{
			uploadRequest.setUploadId(oidService.getOid());
			uploadRequest.setUploadTime(new Date());
			uploadService.upload(uploadRequest);
			message = "upload Successful!";
		}catch(Exception e){
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
		return new ModelAndView("message");
	}
	
}
