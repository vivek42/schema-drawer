package com.crawler.schema.model;

import java.util.Date;

public class Event {

	Long eventId;
	String eventCode;
	Date eventTime;
	String message;
	String stackTrack;
	String applicationName;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStackTrack() {
		return stackTrack;
	}
	public void setStackTrack(String stackTrack) {
		this.stackTrack = stackTrack;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}
