package com.crawler.schema.web.service;

import java.util.List;

import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.model.UploadRequest;
import com.crawler.schema.web.model.UploadRow;

public class UploadService {
	
	private UploadDao uploadDao;
	
	public UploadService(UploadDao dao){
		this.uploadDao = dao;
	}

	public void upload(UploadRequest uploadRequest, byte[] uploadContent, String username, Long uploadUserXrefId) {
		uploadDao.upload(uploadRequest, uploadContent, username, uploadUserXrefId);
	}

	public List<UploadRow> getUploadHistoryForUsername(String username) {
		return uploadDao.getUploadHistoryForUsername(username);
	}
}
