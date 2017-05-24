package com.niit.collaborationPlatform.DAO;

import java.util.List;

import com.niit.collaborationPlatform.model.User;

public interface UserDAO {

	public boolean SaveUser(User user);
	
	public boolean UpdateUser(User user);
	
	public boolean DeleteUser(User user);
	
	public User getById(String emailId);
	
	public List<User > list();
	
	public List<User> getAllList(String emailId);
	
	public void setOnline(String username);
	
	public void setOffline(String username);
	
	public User isValidUser(String emailid, String password);
	
}
