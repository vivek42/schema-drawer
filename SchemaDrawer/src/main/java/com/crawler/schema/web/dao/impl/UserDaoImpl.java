package com.crawler.schema.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crawler.schema.web.config.DBConnection;
import com.crawler.schema.web.dao.BaseDao;
import com.crawler.schema.web.dao.UserDao;
import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Autowired
    public UserDaoImpl(Connection connection) {
    }

	@Override
	public void addUser(User user) {
		try {
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
		}
	}

	@Override
	public void editUser(User user) {
		try {
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
		}
	}

	@Override
	public void deleteUser(User user) {
		try {
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("delete from userandroles where user_id = ?");
			ps.setLong(1, user.getUserId());
			ps.executeUpdate();
			ps = conn.prepareStatement("delete from user where user_id = ?");
			ps.setLong(1, user.getUserId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			commitConnection();
			closeConnection();
		}
	}

	@Override
	public User findUser(int userId) {
		User user = new User();
		user.setUserId(userId);
		List<Role> roles = new ArrayList<Role>();
		user.setRoles(roles);
		try {
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
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
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<User>();
		try {
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
		}
		return allUsers;
	}

	@Override
	public void persistUserProfile(UserProfile userProfile) {
		try{
			Connection conn = getConnection();
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
		}finally{
			commitConnection();
			closeConnection();
		}
			
	}

	@Override
	public UserProfile getUserProfileByName(String username) {
		UserProfile profile = new UserProfile();
		profile.setUsername(username);
		try{
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("select p.* from user_profile p join user u on u.user_id = p.user_id where u.username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				profile.setDob(rs.getTimestamp("dob"));
				profile.setEmailAddress(rs.getString("email"));
				profile.setFirstName(rs.getString("firstname"));
				profile.setLastName(rs.getString("lastname"));
				profile.setGender(rs.getString("gender"));
			}else{
				throw new Exception("Profile not found! for username <" + username + ">");
			}
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			commitConnection();
			closeConnection();
		}
		return profile;
	}

	@Override
	public void updateUserProfile(UserProfile profile) {
		try{
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement("update user_profile set firstname=?, lastname=?, email=?, gender=? where user_id in (select user_id from user where username = ?)");
			ps.setString(1, profile.getFirstName());
			ps.setString(2, profile.getLastName());
			ps.setString(3, profile.getEmailAddress());
			ps.setString(4, profile.getGender());
			ps.setString(5, profile.getUsername());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			commitConnection();
			closeConnection();
		}
	}
}
