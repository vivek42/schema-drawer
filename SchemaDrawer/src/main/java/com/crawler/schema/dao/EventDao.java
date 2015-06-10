package com.crawler.schema.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crawler.schema.model.Event;

@Repository
public class EventDao {

	public void logEvent(Event event) {
		// TODO Auto-generated method stub
		System.out.println("Event successfully logged!");
	}

//    private JdbcTemplate jdbcTemplate;

//    @Autowired
//    public void setDataSource(DataSource dataSource) {
////        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
}
