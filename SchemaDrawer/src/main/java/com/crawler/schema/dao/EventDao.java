package com.crawler.schema.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.crawler.schema.model.Event;

@Repository
public class EventDao {
	
	public static final String INSERT_EVENT = "insert into events (EVENT_ID, EVENT_CODE,EVENT_TIME, MESSAGE, STACK_TRACE, APPLICATION_NAME) "
														+ "values (?, ?, ?, ?, ?,?)";

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    
    public void logEvent(Event event) {
    	jdbcTemplate.update(INSERT_EVENT, 
    			event.getEventId().intValue(), event.getEventCode(), 
    			event.getEventTime(), event.getMessage(), event.getStackTrack(), event.getApplicationName());
    	System.out.println("Event successfully logged!");
    }
}
