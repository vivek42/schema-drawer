package com.crawler.schema.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.crawler.schema.web.config.GraphOutputFormat;
import com.crawler.schema.web.config.TextOutputFormat;
import com.crawler.schema.web.model.CommandEnum;
import com.crawler.schema.web.model.Event;
import com.crawler.schema.web.model.OutputEnum;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;

@Controller
@SessionAttributes("uploadRequest")
public class HomeController {
	
	private static final String MESSAGE_FOR_ERROR_IN_READING_DATABASE = "An error occured while connecting with the database."
			+ "Please check the sqlite database file and try again";

	private static Logger LOGGER = Logger.getLogger(HomeController.class);
	
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
	public ModelAndView userHome(Model model, Principal principal) {
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
		request.setAttribute("message", message);
		return new ModelAndView("redirect:/admin/upload");
	}
	
	@RequestMapping(value="/download/file", method = RequestMethod.POST)
	public void fileDownload(@ModelAttribute UploadRow uploadRow,HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
		InputStream in = uploadService.getDownloadStreamForFile(uploadRow, principal.getName());
		inputStreamToDownload(uploadRow.getFileName(), in, response, "");
	}
	
	
	@RequestMapping(value="/download/diagram", method = RequestMethod.POST) 
	public void diagramDownload(@ModelAttribute UploadRow uploadRow, HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
		InputStream in = uploadService.getDownloadStreamForDiagram(uploadRow, principal.getName());
		inputStreamToDownload(uploadRow.getFileName(), in, response, "_output.html");
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ModelAndView home(Model model, Principal principal) {
		return guestHome(model, principal);
	}

	
	@RequestMapping(value="/guest", method = RequestMethod.GET)
	public ModelAndView guestHome(Model model, Principal principal) {
		if(principal != null) {
			return new ModelAndView("redirect:/admin/upload");
		} else {
			model.addAttribute("uploadRequest", new UploadRequest());
			model.addAttribute("commandList", CommandEnum.getAllCommandEnum());
			model.addAttribute("outputList", OutputEnum.getAllOutputEnum());
			model.addAttribute("textOutputList", TextOutputFormat.getActiveTextOutputFormatList());
			model.addAttribute("graphOutputList", GraphOutputFormat.getActiveGraphOutputFormatList());
			return new ModelAndView("guest");
		}
	}
	
	@RequestMapping(value="/guest", method = RequestMethod.POST)
	public void guestDiagramDownload(@ModelAttribute UploadRequest uploadRequest, HttpServletRequest request, HttpServletResponse response,Principal principal) throws IOException {
		try {
			File dbFile = File.createTempFile(uploadRequest.getUploadContentFile().getOriginalFilename(), "");
			InputStream in = null ;
			uploadRequest.getUploadContentFile().transferTo(dbFile);
			in = uploadService.generateOutputFromFileForGuest("", dbFile);
			inputStreamToDownload(dbFile.getName(), in, response, "_output.html");
		} catch (Exception e) {
			LOGGER.info("Unable to generate diagram for file : <" + uploadRequest.getUploadContentFile().getOriginalFilename() + ">");
		} 
	}
	
	protected void inputStreamToDownload(String filename, InputStream in, HttpServletResponse response, String fileExtension) throws IOException {
		try {
			//response.setContentType("text/html");
			response.setHeader("Content-Disposition","filename=" + filename + fileExtension);
			response.setContentType("image/jpeg");
			IOUtils.copy(in, response.getOutputStream());
		} catch (Exception e) {
			in = handleDownloadDiagramError(filename);
			try {
				IOUtils.copy(in, response.getOutputStream());
			} catch (IOException ioe) {
				LOGGER.info("IOException while trying to copy the file : <" + filename + "> to output stream");
				throw ioe;
			}
		}finally {
			IOUtils.closeQuietly(in);
		}
	}

	protected InputStream handleDownloadDiagramError(String fileName) {
		InputStream in = null;
		LOGGER.info("Unable to generate diagram for file : <" + fileName + ">");
		try {
			File errorFile = File.createTempFile(fileName + "_output.html", "");
			BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile));
			writer.write(MESSAGE_FOR_ERROR_IN_READING_DATABASE);
			writer.close();
			in = new FileInputStream(errorFile);
		} catch (IOException e1) {
			LOGGER.info("Unable to create a tmp file : <" + fileName + ">");
		}
		return in;
	}
}
