package com.crawler.schema.web.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBConnectionPool {
	
	private static DBConnectionPool dbConnectionSingleton = null;
    private boolean flag = true;
 
    /** A private Constructor prevents any other class from instantiating. */
    private DBConnectionPool() {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } 
        catch (InstantiationException e) {
            flag = false;
        } 
        catch (IllegalAccessException e) {
            flag = false;
        } 
        catch (ClassNotFoundException e) {
            flag = false;
        }
        if (flag) {
            openConnection();
        }
    }
     
    public Connection openConnection() {
    	Connection conn;
        try {
        	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    		dataSource.setDriverClassName("org.sqlite.JDBC");
    		dataSource.setUrl("jdbc:sqlite:/home/vivek/schemaDrawerDB/db");
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            return conn;
        } 
        catch (SQLException e) {
            flag = false;
        }
        return null;
    }
 
    /** Static 'instance' method */
    public static DBConnectionPool getInstance() {
        if (dbConnectionSingleton == null) {
            dbConnectionSingleton = new DBConnectionPool();
        }
        return dbConnectionSingleton;
    }
 
    public boolean getConnectionStatus() {
        return flag;
    }
}
