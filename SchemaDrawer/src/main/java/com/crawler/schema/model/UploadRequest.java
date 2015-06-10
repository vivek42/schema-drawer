package com.crawler.schema.model;

import java.util.Date;

public class UploadRequest {
	
	Long uploadId;
	String uploadContent;
	Date uploadTime;
	public Long getUploadId() {
		return uploadId;
	}
	public void setUploadId(Long uploadId) {
		this.uploadId = uploadId;
	}
	public String getUploadContent() {
		return uploadContent;
	}
	public void setUploadContent(String uploadContent) {
		this.uploadContent = uploadContent;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	
}
