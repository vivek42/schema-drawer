package com.crawler.schema.web.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.model.DatabaseExistException;
import com.crawler.schema.web.model.SchemaCrawlerUtility;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

import schemacrawler.crawl.SchemaCrawler;

public class UploadService {
	
	private UploadDao uploadDao;
	private SchemaCrawler crawler;
	
	public UploadService(UploadDao dao){
		this.uploadDao = dao;
	}

	public void upload(UploadRequest uploadRequest, byte[] uploadContent, String username, Long uploadUserXrefId) {
		uploadDao.upload(uploadRequest, uploadContent, username, uploadUserXrefId);
	}

	public List<UploadRow> getUploadHistoryForUsername(String username) {
		return uploadDao.getUploadHistoryForUsername(username);
	}
	
	public InputStream getDownloadStreamForFile(UploadRow row,String username){
		return uploadDao.getDownloadStreamForFile(row,username);
	}
	
	public InputStream getDownloadStreamForDiagram(UploadRow row, String username) {
		try {
			// step 1 : get the content of the file and create a db file 
			String content = uploadDao.getUploadContentByRow(row, username);
			File dbFile = new File(username + ":" + row.getFileName());
			if(dbFile.exists()) {
				throw new DatabaseExistException("the db file with name : " + row.getFileName() +
						"for sqlite already exists for user with username : <" + username);
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(dbFile.getAbsolutePath()));
			writer.write(content);
			writer.close();
			
			// step 2 : make a db connection from the file
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
    		dataSource.setDriverClassName("org.sqlite.JDBC");
    		dataSource.setUrl("jdbc:sqlite:" + dbFile.getAbsolutePath());
    		
    		// step 3 : run SchemaCrawler on the database
    		String outputFileName = username + ":" + row.getFileName() + "_output.html";
    		Path outputFilePath = SchemaCrawlerUtility.runSchemaCrawler(dataSource.getConnection(), outputFileName);
    		
			// step 4 : add file to the outgoing stream.
    		InputStream stream = new FileInputStream(outputFilePath.toFile());
			return stream;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabaseExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
