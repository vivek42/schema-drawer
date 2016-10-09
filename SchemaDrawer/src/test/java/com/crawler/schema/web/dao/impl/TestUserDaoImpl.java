package com.crawler.schema.web.dao.impl;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
		objectUnderTest = spy(objectUnderTest);
		when(objectUnderTest.getConnectionForMethod()).thenReturn(mockConnection);
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
	
	@Test
	public void testGetUserProfileByName() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockRs.getTimestamp(eq("dob"))).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(mockRs.getString(eq("email"))).thenReturn("email");
		when(mockRs.getString(eq("firstname"))).thenReturn("firstname");
		when(mockRs.getString(eq("lastname"))).thenReturn("lastname");
		when(mockRs.getString(eq("gender"))).thenReturn("gender");
		when(mockRs.next()).thenReturn(true);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockRs);
		
		UserProfile profile = objectUnderTest.getUserProfileByName("");
		assertEquals("email", profile.getEmailAddress());
		assertEquals("firstname", profile.getFirstName());
		assertEquals("lastname",profile.getLastName());
		assertEquals("gender", profile.getGender());
	}
	
	@Test
	public void testGetUserProfileByName_Exception() throws SQLException {
		ResultSet mockRs = mock(ResultSet.class);
		when(mockRs.next()).thenReturn(false);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockRs);
		
		UserProfile profile = objectUnderTest.getUserProfileByName("");
		assertNull(profile.getEmailAddress());
		assertNull(profile.getFirstName());
		assertNull(profile.getLastName());
		assertNull(profile.getGender());
	}
	
	@Test
	public void testUpdateUserProfile() throws SQLException {
		UserProfile profile = new UserProfile();
		objectUnderTest.updateUserProfile(profile);
		verify(mockPreparedStatement, times(1)).executeUpdate();
	}
	
	@Test
	public void testCoverExceptions() throws SQLException {
		when(mockConnection.prepareStatement(any(String.class))).thenThrow(new SQLException());
		try {
			objectUnderTest.addUser(null);
			objectUnderTest.editUser(null);
			objectUnderTest.deleteUser(null);
			objectUnderTest.findUser(0);
			objectUnderTest.findUserByName(null);
			objectUnderTest.getAllUsers();
			objectUnderTest.persistUserProfile(null);
			objectUnderTest.updateUserProfile(null);
		} catch(Exception e) {
			fail("not expected to throw an exception");
		}
	}
	
	@After
	public void tearDown() {
		
	}

}
