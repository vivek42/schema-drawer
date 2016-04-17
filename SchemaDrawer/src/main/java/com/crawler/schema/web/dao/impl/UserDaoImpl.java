package com.crawler.schema.web.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;

public class UserDaoImpl implements UserDao {

	private DataSource dataSource;
	
    @Autowired
    public UserDaoImpl(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void addUser(User user) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into user values(?, ?, ?, ?)");
			ps.setLong(1, user.getUserId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, (user.isActive() ? "ACTIVE":"INACTIVE"));
			ps.executeUpdate();
			for(Role role : user.getRoles()){
				ps = conn.prepareStatement("insert into usersandroles values(?, ?)");
				ps.setLong(1, user.getUserId());
				ps.setInt(2, role.getRoleId());
				ps.executeUpdate();
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void editUser(User user) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("update user set username=?, password=?, status=? where user_id=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, (user.isActive() ? "ACTIVE" : "INACTIVE"));
			ps.setLong(4, user.getUserId());
			ps.executeUpdate();
			ps = conn.prepareStatement("delete from userandroles where user_id = ?");
			ps.setLong(1, user.getUserId());
			ps.executeUpdate();
			for(Role role : user.getRoles()){
				ps = conn.prepareStatement("insert into userandroles values(?, ?");
				ps.setLong(1, user.getUserId());
				ps.setInt(2, role.getRoleId());
				ps.executeUpdate();
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(User user) {
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from userandroles where user_id = ?");
			ps.setLong(1, user.getUserId());
			ps.executeUpdate();
			ps = conn.prepareStatement("delete from user where user_id = ?");
			ps.setLong(1, user.getUserId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findUser(int userId) {
		User user = new User();
		user.setUserId(userId);
		List<Role> roles = new ArrayList<Role>();
		user.setRoles(roles);
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from user where user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user.setActive("ACTIVE".equals(rs.getString("status")));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
			ps = conn.prepareStatement("select ur.*, r.rolename as rolename from usersandroles ur "
					+ "join role r on ur.role_id = r.role_id where user_id=?");
			rs = ps.executeQuery();
			while(rs.next()){
				Role role = Role.getRoleByName(rs.getString("roleName"));
				user.getRoles().add(role);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User findUserByName(String username) {
		User user = new User();
		user.setUsername(username);
		List<Role> roles = new ArrayList<Role>();
		user.setRoles(roles);
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user.setActive("ACTIVE".equals(rs.getString("status")));
				user.setUserId(rs.getLong("user_id"));
				user.setPassword(rs.getString("password"));
			}
			ps = conn.prepareStatement("select ur.*, r.rolename as rolename from usersandroles ur "
					+ "join role r on ur.role_id = r.role_id where user_id=?");
			ps.setLong(1, user.getUserId());
			rs = ps.executeQuery();
			while(rs.next()){
				Role role = Role.getRoleByName(rs.getString("rolename"));
				user.getRoles().add(role);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from user");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setActive("ACTIVE".equals(rs.getString("status")));
				user.setUserId(rs.getLong("user_id"));
				user.setPassword(rs.getString("password"));
				allUsers.add(user);
				// TODO: populate the roles for user
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	@Override
	public void persistUserProfile(UserProfile userProfile) {
		try{
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("insert into user_profile(user_id, firstname, lastname, email, dob, gender) values(?, ?, ?, ?, ?, ?)");
			ps.setLong(1, userProfile.getId());
			ps.setString(2, userProfile.getFirstName());
			ps.setString(3, userProfile.getLastName());
			ps.setString(4, userProfile.getEmailAddress());
			ps.setDate(5, new java.sql.Date(userProfile.getDob().getTime()));
			ps.setString(6, userProfile.getGender());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
}
