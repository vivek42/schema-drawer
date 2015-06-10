package com.crawler.schema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crawler.schema.dao.EventDao;
import com.crawler.schema.dao.UploadDao;
import com.crawler.schema.service.EventService;
import com.crawler.schema.service.OidService;
import com.crawler.schema.service.UploadService;


@Configuration
public class AppConfig {

	@Bean
	public UploadService getUploadService(){
		return new UploadService(new UploadDao());
	}
	
	@Bean
	public EventService getEventService(){
		return new EventService(new EventDao());
	}
	
	@Bean
	public OidService getOidService(){
		return new OidService();
	}
}
