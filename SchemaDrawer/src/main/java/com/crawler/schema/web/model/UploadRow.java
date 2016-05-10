package com.crawler.schema.web.model;

import java.sql.Timestamp;

public class UploadRow {
	Long serialNumber;
	String fileName;
	Timestamp uploadTime;
	boolean needsDownload = false;
	boolean needsSchemaGeneration = false;
	public Long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public boolean needsDownload() {
		return needsDownload;
	}
	public void setNeedsDownload(boolean needsDownload) {
		this.needsDownload = needsDownload;
	}
	public boolean needsSchemaGeneration() {
		return needsSchemaGeneration;
	}
	public void setNeedsSchemaGeneration(boolean needsSchemaGeneration) {
		this.needsSchemaGeneration = needsSchemaGeneration;
	}

}
