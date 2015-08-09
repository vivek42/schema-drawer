package com.crawler.schema.web.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.web.model.UploadRequest;

public class UploadDao {
	
	public static final String INSERT_UPLOAD = "insert into uploads (upload_id, content, upload_time, file_name)"
														  + "values (?,?,?,?)";
	
    private DataSource dataSource;

    @Autowired
    public UploadDao(DataSource dataSource) {
        this.dataSource = dataSource;
        
    }

	public void upload(UploadRequest uploadRequest, byte[] uploadContent) {
		try {
			
			Connection conn = dataSource.getConnection();
			// Converting byte[] into input stream
			InputStream uploadStream = new ByteArrayInputStream(uploadContent);
			
			
			PreparedStatement ps = conn.prepareStatement(INSERT_UPLOAD);
			ps.setInt(1, uploadRequest.getUploadId().intValue());
			ps.setBinaryStream(2, uploadStream);
			java.util.Date currentDate = new java.util.Date();
			ps.setTimestamp(3, new Timestamp(currentDate.getTime()));
			ps.setString(4, uploadRequest.getFileName());
			ps.executeUpdate();
			ps.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
