package com.crawler.schema.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.crawler.schema.model.UploadRequest;

public class UploadDao {
	
	public static final String INSERT_UPLOAD = "insert into uploads (upload_id, content, upload_time)"
														  + "values (?,?,?)";
	
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UploadDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	public void upload(UploadRequest uploadRequest) {
		try{
			jdbcTemplate.update(INSERT_UPLOAD, 
					uploadRequest.getUploadId().intValue(), uploadRequest.getUploadContent(), uploadRequest.getUploadTime());
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
