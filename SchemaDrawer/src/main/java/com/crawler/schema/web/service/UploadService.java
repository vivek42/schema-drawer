package com.crawler.schema.web.service;

import com.crawler.schema.web.dao.UploadDao;
import com.crawler.schema.web.model.UploadRequest;

public class UploadService {
	
	private UploadDao uploadDao;
	
	public UploadService(UploadDao dao){
		this.uploadDao = dao;
	}

	public void upload(UploadRequest uploadRequest, byte[] uploadContent) {
		uploadDao.upload(uploadRequest, uploadContent);
	}
}
