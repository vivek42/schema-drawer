package com.crawler.schema.web.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.schema.web.model.Event;

@Repository
public class EventDao {
	
	public static final String INSERT_EVENT = "insert into events (EVENT_ID, EVENT_CODE,EVENT_TIME, MESSAGE, STACK_TRACE, APPLICATION_NAME) "
														+ "values (?, ?, ?, ?, ?,?)";

	private DataSource dataSource;

    @Autowired
    public EventDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    
    public void logEvent(Event event) {
    	Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_EVENT);
			ps.setInt(1, event.getEventId().intValue());
			ps.setString(2, event.getEventCode());
			ps.setDate(3, new Date(event.getEventTime().getTime()));
			ps.setString(4, event.getMessage());
			ps.setString(5, event.getStackTrack());
			ps.setString(6, event.getApplicationName());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
    }
}
