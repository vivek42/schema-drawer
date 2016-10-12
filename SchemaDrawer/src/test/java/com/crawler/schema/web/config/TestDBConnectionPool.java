package com.crawler.schema.web.config;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class TestDBConnectionPool {
	
	DBConnectionPool objectUnderTest;

	@Test
	public void testOpenConnection() throws SQLException {
		objectUnderTest = DBConnectionPool.getInstance();
		Connection conn = objectUnderTest.openConnection();
		assertNotNull(conn);
		conn.close();
	}

}
