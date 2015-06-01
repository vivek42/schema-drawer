package com.crawler.schema.model;

import java.util.Date;

public class UploadRequest {
	
	int uploadId;
	String uploadContent;
	Date uploadTime;
	public int getUploadId() {
		return uploadId;
	}
	public void setUploadId(int uploadId) {
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
