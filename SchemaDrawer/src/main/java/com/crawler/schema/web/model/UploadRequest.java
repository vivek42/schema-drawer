package com.crawler.schema.web.model;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class UploadRequest {
	
	Long uploadId;
	MultipartFile uploadContentFile;
	Date uploadTime;
	String fileName;
	public Long getUploadId() {
		return uploadId;
	}
	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadContentFile() {
		return uploadContentFile;
	}
	public void setUploadContentFile(MultipartFile uploadContentFile) {
		this.uploadContentFile = uploadContentFile;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	public String getUploadContent() throws IOException{
		return new String(getUploadContentFile().getBytes());
	}
	
}
