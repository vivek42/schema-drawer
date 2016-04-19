package com.crawler.schema.web.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DBConnection {
	
	private static DBConnection dbConnectionSingleton = null;
    private static Connection conn = null;
    private boolean flag = true;
 
    /** A private Constructor prevents any other class from instantiating. */
    private DBConnection() {
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
        if (conn == null) {
            try {
            	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        		dataSource.setDriverClassName("org.sqlite.JDBC");
        		dataSource.setUrl("jdbc:sqlite:/Users/vivekchouhan/sqliteDb/schemaDrawer.db");
                conn = dataSource.getConnection();
            } 
            catch (SQLException e) {
                flag = false;
            }
        }
        return conn;
    }
 
    /** Static 'instance' method */
    public static DBConnection getInstance() {
        if (dbConnectionSingleton == null) {
            dbConnectionSingleton = new DBConnection();
        }
        return dbConnectionSingleton;
    }
 
    public boolean getConnectionStatus() {
        return flag;
    }
}
