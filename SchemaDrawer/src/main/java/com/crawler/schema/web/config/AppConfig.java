package com.crawler.schema.web.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.crawler.schema.web.dao.EventDao;
import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.dao.impl.UserDaoImpl;
import com.crawler.schema.web.service.EventService;
import com.crawler.schema.web.service.OidService;
import com.crawler.schema.web.service.UploadService;
import com.crawler.schema.web.service.UserService;
import com.crawler.schema.web.service.impl.UserServiceImpl;


@Configuration
public class AppConfig {

	@Bean
	public UploadService getUploadService(){
		return new UploadService(new UploadDao(getDataSource()));
	}
	
	@Bean
	public UserServiceImpl getUserService() {
		return new UserServiceImpl(new UserDaoImpl(getDataSource()));
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
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl("jdbc:sqlite:/Users/vivekchouhan/sqliteDb/schemaDrawer.db");
		
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/schema_drawer");
//        dataSource.setUsername("vivek");
//        dataSource.setPassword("P@ssword");
         
        return dataSource;
    }
	
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver(){
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setMaxUploadSize(100000);
		return resolver;
	}
	
}
