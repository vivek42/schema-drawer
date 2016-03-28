package com.crawler.schema.web;

import java.util.Date;
import java.util.Locale;

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
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;

@Controller
@SessionAttributes("uploadRequest")
public class HomeController {
	
	protected UploadService uploadService;
	protected EventService eventService;
	protected OidService oidService;
	
	@Autowired
	public HomeController(UploadService uploadService, EventService eventService, OidService oidService) {
		this.uploadService = uploadService;
		this.eventService = eventService;
		this.oidService = oidService;
	}
	
	@RequestMapping(value = "/home" , method = RequestMethod.GET)
	public ModelAndView redirectHome(Locale locale, Model model) {
		return new ModelAndView("redirect:/admin/upload");
	}
	
	@RequestMapping(value = "/admin/upload", method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		model.addAttribute("uploadRequest", new UploadRequest());
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public ModelAndView submitUploadContent(@ModelAttribute UploadRequest uploadRequest, HttpServletRequest request){
		String message;
		try{
			uploadRequest.setUploadId(oidService.getOid());
			uploadRequest.setUploadTime(new Date());
			byte[] uploadContent = uploadRequest.getUploadContentFile().getBytes();
			uploadService.upload(uploadRequest, uploadContent);
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
		return new ModelAndView("home");
	}
	
	@RequestMapping(value = "/admin/privatePage", method = RequestMethod.GET)
	public String privatePage() {
		return "testPrivatePage";
	}
	
}
