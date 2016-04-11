package com.crawler.schema.web.service;

import com.crawler.schema.web.dao.EventDao;
import com.crawler.schema.web.model.Event;

public class EventService {
	
	private EventDao eventDao;
	private OidService oidService;
	
	public EventService(EventDao eventDao, OidService oidService){
		this.eventDao = eventDao;
		this.oidService = oidService;
	}

	public void logEvent(Event event) {
		event.setEventId(oidService.getOid());
		eventDao.logEvent(event);
	}

}
