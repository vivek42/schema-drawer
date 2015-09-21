package com.crawler.schema.web.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.User;

public class UserDaoImpl implements UserDao {

	private DataSource dataSource;
	
    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
}
