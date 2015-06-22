package com.crawler.schema.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.crawler.schema.dao.EventDao;
import com.crawler.schema.dao.UploadDao;
import com.crawler.schema.service.EventService;
import com.crawler.schema.service.OidService;
import com.crawler.schema.service.UploadService;


@Configuration
public class AppConfig {

	@Bean
	public UploadService getUploadService(){
		return new UploadService(new UploadDao(getDataSource()));
	}
	
	@Bean
	public EventService getEventService(){
		return new EventService(new EventDao(getDataSource()));
	}
	
	@Bean
	public OidService getOidService(){
		return new OidService();
	}
	
	@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/schema-drawer");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
         
        return dataSource;
    }
	
//	@Bean
//	public BasicDataSource getDataSource(){
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/schema-drawer");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//		return dataSource;
//	}

}
