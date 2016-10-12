package com.crawler.schema.integration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.crawler.schema.web.config.DBConnectionPool;

public class PrepareDatabase {
	
	public static Logger LOGGER = Logger.getLogger(PrepareDatabase.class);
	
	public void prepare() throws Exception {
		dropTables();
		createTables();
		insertData();
	}

	private void insertData() throws Exception {
		LOGGER.info("populating tables..........");
		executeSqlFromFile("database/insert.sql");
		LOGGER.info("population of data finished ..........");
	}
	
	private void createTables() throws Exception {
		LOGGER.info("creating tables..........");
		executeSqlFromFile("database/create.sql");
		LOGGER.info("creation of tables finished ..........");
	}

	private void dropTables() throws Exception {
		LOGGER.info("Deleting and dropping data ..........");
		executeSqlFromFile("database/delete_and_drop.sql");
		LOGGER.info("Deletion  and drop finished ..........");
	}

	private void executeSqlFromFile(String scriptPath) throws Exception {
		ClassLoader classLoader = this.getClass().getClassLoader();
    	String scriptFileAbsolutePath = new File(classLoader.getResource(scriptPath).getFile()).getAbsolutePath();
    	List<String> sqlCommands = new ArrayList<>();
    	try (Connection conn = DBConnectionPool.getInstance().openConnection()) {
			BufferedReader reader = new BufferedReader(new FileReader(new File(scriptFileAbsolutePath)));
			StringBuilder builder = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) {
				if(line.trim().endsWith(";")) {
					builder.append(line);
					sqlCommands.add(builder.toString());
					builder = new StringBuilder();
				} else {
					builder.append(line.trim()).append(" ");
				}
			}
			reader.close();
			for(String sqlComm : sqlCommands) {
				LOGGER.info("Executing .........." + sqlComm );
				
				PreparedStatement stmt = conn.prepareStatement(sqlComm);
				stmt.executeUpdate();
				stmt.close();
			}
			conn.commit();
		} catch (IOException | SQLException e) {
			LOGGER.info(e);
			throw e;
		}
	}
	
	public static void main(String[] args) {
		PrepareDatabase db = new PrepareDatabase();
		try {
			db.prepare();
		} catch(Exception e) {
			System.out.println("database build failed. Exiting..");
		}

	}
}
