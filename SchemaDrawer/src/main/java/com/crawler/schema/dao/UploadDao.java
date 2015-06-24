package com.crawler.schema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.model.UploadRequest;

public class UploadDao {
	
	public static final String INSERT_UPLOAD = "insert into uploads (upload_id, content, upload_time, file_name)"
														  + "values (?,?,?,?)";
	
    private DataSource dataSource;

    @Autowired
    public UploadDao(DataSource dataSource) {
        this.dataSource = dataSource;
        
    }

	public void upload(UploadRequest uploadRequest, String uploadContent) {
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_UPLOAD);
			ps.setInt(1, uploadRequest.getUploadId().intValue());
			ps.setString(2, uploadContent);
			ps.setDate(3, new Date(uploadRequest.getUploadTime().getTime()));
			ps.setString(4, uploadRequest.getFileName());
			ps.executeUpdate();
			ps.close();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// Do nothing
				}
			}
		}
	}

}
