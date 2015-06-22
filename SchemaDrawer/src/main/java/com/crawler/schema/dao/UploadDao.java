package com.crawler.schema.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.crawler.schema.model.UploadRequest;

public class UploadDao {
	
	public static final String INSERT_UPLOAD = "insert into uploads (upload_id, content, upload_time)"
														  + "values (?,?,?)";
	
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Autowired
    public UploadDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
        
    }

	public void upload(UploadRequest uploadRequest) {
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_UPLOAD);
			ps.setInt(1, uploadRequest.getUploadId().intValue());
			ps.setString(2, uploadRequest.getUploadContent());
			ps.setDate(3, new Date(uploadRequest.getUploadTime().getTime()));
			ps.executeUpdate();
			ps.close();
		
//			jdbcTemplate.update(INSERT_UPLOAD, 
//					uploadRequest.getUploadId().intValue(), uploadRequest.getUploadContent(), uploadRequest.getUploadTime());
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
