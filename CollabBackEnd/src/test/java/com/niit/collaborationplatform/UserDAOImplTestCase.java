package com.niit.collaborationplatform;

import javax.servlet.http.HttpSession;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaborationPlatform.DAO.UserDAO;
import com.niit.collaborationPlatform.model.User;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class UserDAOImplTestCase {

	@Autowired
	static UserDAO userDAO;
	
	@Autowired
	static User user;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	static AnnotationConfigApplicationContext context;

	@BeforeClass
	public static void init()
	{
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		userDAO=(UserDAO) context.getBean("userDAOImpl");
		user=(User) context.getBean("user");
	}
	
	@Test
	public void SaveUserTestCase()
	{
		user.setEmailId("srinivas@gmail.com");
		user.setUsername("srinivas");
		user.setPassword("123456");
		user.setRole("User");
		user.setMobile("809089087");
		user.setGender("Male");
		user.setStatus("Valid");
		user.setIsOnline("Y");
		user.setReason("Valid User");
		
		Assert.assertEquals("SaveUserTestCase", false, userDAO.SaveUser(user));
	}
	/*
	// @Test
	public void DeleteUserTestCase()
	{
	user=userDAO.getById("kamat@gmail.com");
	boolean status=userDAO.DeleteUser(user);
	Assert.assertEquals("DeleteUserTestCase", false, status);
	
	}
	
	//@Test
	public void UpdateUserTestCase()
	{
		user=userDAO.getById("kaustubh235@gmail.com");
		user.setMobile("789076547");
		boolean status=userDAO.UpdateUser(user);
		Assert.assertEquals("UpdateUserTestCase", false, status);
	}
	
	*/
}
