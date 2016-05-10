package com.crawler.schema.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

@Controller
@RequestMapping("/download")
public class DownloadController {

	private static final int BUFFER_SIZE = 4096;

	private String filePath = "/downloads/SpringProject.zip";
	
	@Value("#{'resources/images/sample.jpg'}")
	private Resource resource;
	
	@RequestMapping(value="/txt", method = RequestMethod.GET)
	public void textDownload(@ModelAttribute UploadRow uploadRow,HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream in = resource.getInputStream();
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
			"attachment;filename=downloadfilename.csv");
			//response.setContentType("image/jpeg");
			IOUtils.copy(in, response.getOutputStream());
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

//	@RequestMapping(value="/uploaded/file", method = RequestMethod.GET)
//	public void doDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		ServletContext context = request.getSession().getServletContext();
//		String appPath = context.getRealPath("");
//		System.out.println("appPath = " + appPath);
//
//		String fullPath = appPath + filePath;
//		File downloadFile = new File(fullPath);
//		FileInputStream inputStream = new FileInputStream(downloadFile);
//		String mimeType = context.getMimeType(fullPath);
//		if (mimeType == null) {
//			// set to binary type if MIME mapping not found
//			mimeType = "application/octet-stream";
//		}
//		System.out.println("MIME type: " + mimeType);
//	
//		response.setContentType(mimeType);
//		response.setContentLength((int) downloadFile.length());
//		String headerKey = "Content-Disposition";
//		String headerValue = String.format("attachment; filename=\"%s\"",
//		downloadFile.getName());
//		response.setHeader(headerKey, headerValue);
//		
//		OutputStream outStream = response.getOutputStream();
//		
//		byte[] buffer = new byte[BUFFER_SIZE];
//		int bytesRead = -1;
//	
//		while ((bytesRead = inputStream.read(buffer)) != -1) {
//			outStream.write(buffer, 0, bytesRead);
//		}
//	
//		inputStream.close();
//		outStream.close();
//	}
	
//	@ResponseBody
//	@RequestMapping("/photo2", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
//	public byte[] testphoto() throws IOException {
//	    InputStream in = servletContext.getResourceAsStream("/images/no_image.jpg");
//	    return IOUtils.toByteArray(in);
//	}
}

		
