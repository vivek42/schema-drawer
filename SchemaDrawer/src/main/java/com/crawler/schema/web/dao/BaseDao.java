package com.crawler.schema.web.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.crawler.schema.web.config.DBConnectionPool;

public class BaseDao {
	
	private Connection conn;
	
	public Connection getConnection(){
		try{
			if(conn == null || conn.isClosed()){
				return DBConnectionPool.getInstance().openConnection();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	public void commitConnection(){
		try {
			if(conn!=null){
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection(){
		try{
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
