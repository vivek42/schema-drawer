package com.crawler.schema.web;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.crawler.schema.web.model.UploadRow;
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
	public ModelAndView home(Model model, Principal principal) {
		model.addAttribute("uploadRequest", new UploadRequest());
		model.addAttribute("uploadHistory", uploadService.getUploadHistoryForUsername(principal.getName()));
		model.addAttribute("uploadRow", new UploadRow());
		return new ModelAndView("home");
	}
	

	@RequestMapping(value = "/admin/upload", method = RequestMethod.POST)
	public ModelAndView submitUploadContent(@ModelAttribute UploadRequest uploadRequest, HttpServletRequest request, Principal principal){
		String message;
		try{
			uploadRequest.setUploadId(oidService.getOid());
			uploadRequest.setFileName(uploadRequest.getUploadContentFile().getOriginalFilename());
			uploadRequest.setUploadTime(new Date());
			byte[] uploadContent = uploadRequest.getUploadContentFile().getBytes();
			uploadService.upload(uploadRequest, uploadContent, principal.getName(), oidService.getOid());
			message = "upload Successful!";
		}catch(Exception e){
			Event event = new Event(this.getClass().getName() + ".submitUploadContect",e);
			eventService.logEvent(event);
			message = "we have encountered an error";
		}
		// TODO : add request object and service for inserting the uploaded content
		request.setAttribute("message", message);
		return new ModelAndView("redirect:/admin/upload");
	}
	
	@RequestMapping(value="/download/file", method = RequestMethod.POST)
	public void fileDownload(@ModelAttribute UploadRow uploadRow,HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
		InputStream in = uploadService.getDownloadStreamForFile(uploadRow, principal.getName());
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition","attachment;filename=" + uploadRow.getFileName());
			//response.setContentType("image/jpeg");
			IOUtils.copy(in, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	@RequestMapping(value="/download/diagram", method = RequestMethod.POST) 
	public void diagramDownload(@ModelAttribute UploadRow uploadRow, HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
		InputStream in = uploadService.getDownloadStreamForDiagram(uploadRow, principal.getName());
		try {
			//response.setContentType("image/jpeg");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition","attachment;filename=" + uploadRow.getFileName() + "_output.html");
			IOUtils.copy(in, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	@RequestMapping(value="/guest", method = RequestMethod.GET)
	public ModelAndView guestHome(Model model, Principal principal) {
		if(principal == null) {
			return new ModelAndView("redirect:/admin/upload");
		} else {
			model.addAttribute("uploadRequest", new UploadRequest());
			return new ModelAndView("guest");
		}
	}
}
