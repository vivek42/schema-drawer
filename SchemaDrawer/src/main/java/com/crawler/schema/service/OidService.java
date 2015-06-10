package com.crawler.schema.service;

public class OidService {

	public Long getOid(){
		return System.currentTimeMillis();
	}
	
	public Long getOid(Long id){
		return System.currentTimeMillis() + id;
	}
}
