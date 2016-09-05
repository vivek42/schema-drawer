package com.crawler.schema.web.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.web.config.DBConnectionPool;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

public class UploadDao {
	
	public static final String INSERT_UPLOAD = "insert into uploads (upload_id, content, upload_time, file_name)"
														  + "values (?,?,?,?)";
	
    public static final String INSERT_UPLOAD_USER_XREF = "insert into user_upload_xref (id, upload_id, user_id)"
    		+ " values(?, ?, (select user_id from user where username = ?))";
    
    public static final String SELECT_UPLOADS_BY_USER = "select u.file_name, u.upload_time from uploads u "
    		+ "join user_upload_xref x on u.upload_id = x.upload_id "
    		+ "join user ur on ur.user_id = x.user_id "
    		+ "where ur.username = ? "
    		+ "order by upload_time desc";
    
    public static final String SELECT_UPLOAD_CONTENT = "select u.content from uploads u "
    		+ "join user_upload_xref x on u.upload_id = x.upload_id "
    		+ "join user ur on ur.user_id = x.user_id "
    		+ "where ur.username = ? "
    		+ " and u.file_name = ? "
    		+ " and u.upload_time = ? ";
	
    @Autowired
    public UploadDao(Connection connection) {
        //this.connection = connection;
    }

	public void upload(UploadRequest uploadRequest, byte[] uploadContent, String username, Long uploadUserXrefId) {
		try (Connection conn = DBConnectionPool.getInstance().openConnection())
		{
			//Connection conn = connection;
			// Converting byte[] into input stream
			//InputStream uploadStream = new ByteArrayInputStream(uploadContent);
			PreparedStatement ps = conn.prepareStatement(INSERT_UPLOAD);
			ps.setLong(1, uploadRequest.getUploadId());
			ps.setBytes(2, uploadContent);
			java.util.Date currentDate = new java.util.Date();
			ps.setTimestamp(3, new Timestamp(currentDate.getTime()));
			ps.setString(4, uploadRequest.getFileName());
			ps.executeUpdate();
			conn.commit();
			ps.close();
			
			ps = conn.prepareStatement(INSERT_UPLOAD_USER_XREF);
			ps.setLong(1, uploadUserXrefId);
			ps.setLong(2, uploadRequest.getUploadId());
			ps.setString(3, username);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<UploadRow> getUploadHistoryForUsername(String username) {
		List<UploadRow> uploadHistory = new ArrayList<UploadRow>();
		Long counter = 1L;
		try (Connection conn = DBConnectionPool.getInstance().openConnection()){
			PreparedStatement ps = conn.prepareStatement(SELECT_UPLOADS_BY_USER);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UploadRow row = new UploadRow();
				row.setSerialNumber(counter);
				row.setFileName(rs.getString("file_name"));
				row.setUploadTime(rs.getTimestamp("upload_time"));
				uploadHistory.add(row);
				counter++;
			}
			conn.commit();
			rs.close();
			ps.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return uploadHistory;
	}

	public InputStream getDownloadStreamForFile(UploadRow row, String username) {
		InputStream stream = null;
		try (Connection conn = DBConnectionPool.getInstance().openConnection())
		{
			PreparedStatement ps = conn.prepareStatement(SELECT_UPLOAD_CONTENT);
			ps.setString(1, username);
			ps.setString(2, row.getFileName());
			ps.setTimestamp(3, row.getUploadTime());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				stream = rs.getBinaryStream("content");
			}
			rs.close();
			ps.close();
			return stream;
		}catch (Exception e) {
			e.printStackTrace();
		}
		stream = new ByteArrayInputStream("".getBytes());
		return stream;
	}

	public String getUploadContentByRow(UploadRow row, String username) {
		String content = "";
		try (Connection conn = DBConnectionPool.getInstance().openConnection())
		{
			PreparedStatement ps = conn.prepareStatement(SELECT_UPLOAD_CONTENT);
			ps.setString(1, username);
			ps.setString(2, row.getFileName());
			ps.setTimestamp(3, row.getUploadTime());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				content = rs.getString("content");
			}
			rs.close();
			ps.close();
			return content;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
}
