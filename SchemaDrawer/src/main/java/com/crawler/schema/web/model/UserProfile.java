package com.crawler.schema.web.model;

import java.util.Date;

public class UserProfile {
	Long id;
	String username;
	String password;
	String emailAddress;
	String firstName;
	String lastName;
	Date dob;
	String dobShow;
	String gender;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public String getDobShow() {
		if(dob !=null && dob.toString().indexOf(":") >= 2){
			return dob.toString().substring(0, (dob.toString().indexOf(":")-2));
		}else if(dob == null){
			return null;
		}else {
			return dob.toString();
		}
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setId(Long userId) {
		this.id = userId;
	}
	public Long getId() {
		return id;
	}
	
	
}
