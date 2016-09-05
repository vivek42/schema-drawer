package com.crawler.schema.web.model;

import java.sql.Connection;

import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class TestSchemaCrawlerUtility {
	
	SchemaCrawlerUtility objectUnderTest;
	
	@Test
	public void testRunSchemaCrawler() throws Exception {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.sqlite.JDBC");
		dataSource.setUrl("jdbc:sqlite:/home/vivek/Desktop/testDb/test");
		Connection conn = dataSource.getConnection();
		
		String outputfileName = "/home/vivek/Desktop/testDb/tst_output.html";
		
		SchemaCrawlerUtility.runSchemaCrawler(conn, outputfileName);
	}

}
