package com.niit.collaborationPlatform.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Friend extends BaseDomain{

	@Id
	public int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(char isOnline) {
		this.isOnline = isOnline;
	}

	public String emailId;
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String friendEmailId;
	
	public String getFriendEmailId() {
		return friendEmailId;
	}

	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}

	public char status;
	
	public char isOnline;
	
	
}
