package com.crawler.schema.service;

import com.crawler.schema.dao.EventDao;
import com.crawler.schema.model.Event;

public class EventService {
	
	private EventDao eventDao;
	
	public EventService(EventDao eventDao){
		this.eventDao = eventDao;
	}

	public void logEvent(Event event) {
		eventDao.logEvent(event);
		// TODO Auto-generated method stub
		
	}

}
