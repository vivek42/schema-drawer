package com.crawler.schema.web.dao.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.crawler.schema.web.model.Role;
import com.crawler.schema.web.model.User;
import com.crawler.schema.web.model.UserProfile;

public class TestUserDaoImpl extends Mockito {

	Connection mockConnection;
	PreparedStatement mockPreparedStatement;
	
	UserDaoImpl objectUnderTest;
	
	@Before
	public void setup() throws SQLException {
		mockConnection = mock(Connection.class);
		mockPreparedStatement = mock(PreparedStatement.class);
		when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockPreparedStatement);
		objectUnderTest = new UserDaoImpl(mockConnection);
	}
	
	private User createTestUser() {
		User testUser = new User();
		testUser.setActive(true);
		testUser.setUserId(101L);
		testUser.setPassword("tstPwd");
		testUser.setUsername("tstUsrname");
		List<Role> tstRoles = new ArrayList<Role>();
		Role tstRole = Role.ADMIN;
		tstRoles.add(tstRole);
		testUser.setRoles(tstRoles);
		return testUser;
	}
	
	@Test
	public void testAddUser() throws SQLException {
		User tstUser = createTestUser();
		objectUnderTest.addUser(tstUser);
		verify(mockPreparedStatement, times(2)).executeUpdate();
	}
	
	@Test
	public void testEditUser() throws SQLException {
		User tstUser = createTestUser();
		objectUnderTest.editUser(tstUser);
		verify(mockPreparedStatement, times(3)).executeUpdate();
	}
	
	@Test
	public void testDeleteUser() throws SQLException {
		User tstUser = createTestUser();
		objectUnderTest.deleteUser(tstUser);
		verify(mockPreparedStatement, times(2)).executeUpdate();
	}
	
	@Test
	public void testFindUser() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockRs);
		when(mockRs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockRs.getString("status")).thenReturn("ACTIVE");
		when(mockRs.getString("username")).thenReturn("tstUser");
		when(mockRs.getString("password")).thenReturn("tstPasswd");
		when(mockRs.getString("roleName")).thenReturn("admin");
		
		User tstUser = objectUnderTest.findUser(101);
		assertEquals(101,tstUser.getUserId());
		assertEquals(1,tstUser.getRoles().size());
	}
	
	@Test
	public void testFindUserByUsername() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockRs);
		when(mockRs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(mockRs.getString("status")).thenReturn("ACTIVE");
		when(mockRs.getInt("user_id")).thenReturn(101);
		when(mockRs.getString("password")).thenReturn("tstPasswd");
		when(mockRs.getString("roleName")).thenReturn("admin");
		
		User tstUser = objectUnderTest.findUserByName("username");
		assertEquals("username",tstUser.getUsername());
		assertEquals(1,tstUser.getRoles().size());
	}
	
	@Test
	public void testGetAllusers() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockRs);
		when(mockRs.next()).thenReturn(true).thenReturn(false);
		when(mockRs.getString("status")).thenReturn("ACTIVE");
		when(mockRs.getInt("user_id")).thenReturn(101);
		when(mockRs.getString("username")).thenReturn("tstUser");
		when(mockRs.getString("password")).thenReturn("tstPasswd");
		when(mockRs.getString("roleName")).thenReturn("admin");
		
		List<User> userList = objectUnderTest.getAllUsers();
		assertEquals(1,userList.size());
		assertEquals("tstUser",userList.get(0).getUsername());
	}
	
	@Test
	public void testPersistUserProfile() throws SQLException {
		UserProfile tstUserProfile = new UserProfile();
		tstUserProfile.setDob(new Date());
		tstUserProfile.setId(101L);
		tstUserProfile.setFirstName("fname");
		tstUserProfile.setLastName("lastName");
		tstUserProfile.setEmailAddress("abc@def.ghi");
		tstUserProfile.setGender("male");
		objectUnderTest.persistUserProfile(tstUserProfile);
		verify(mockPreparedStatement, times(1)).executeUpdate();
	}
	
	@After
	public void tearDown() {
		
	}

}
