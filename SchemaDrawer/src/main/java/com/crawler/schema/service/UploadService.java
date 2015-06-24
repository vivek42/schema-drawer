package com.crawler.schema.service;

import com.crawler.schema.dao.UploadDao;
import com.crawler.schema.model.UploadRequest;

public class UploadService {
	
	private UploadDao uploadDao;
	
	public UploadService(UploadDao dao){
		this.uploadDao = dao;
	}

	public void upload(UploadRequest uploadRequest, String uploadContent) {
		uploadDao.upload(uploadRequest, uploadContent);
	}
}
