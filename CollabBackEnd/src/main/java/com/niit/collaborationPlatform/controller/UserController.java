package com.niit.collaborationPlatform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationPlatform.DAO.UserDAO;
import com.niit.collaborationPlatform.model.User;

import ch.qos.logback.classic.Logger;

@RestController
public class UserController {

	@Autowired
	public User user;

	@Autowired
	public UserDAO userDAO;

	@Autowired
	public HttpSession session;
	
	
	public org.slf4j.Logger log= LoggerFactory.getLogger(UserController.class);
	
	

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		log.debug("UserController==> Starting getAllUsers method ");
		List<User> users = userDAO.list();
		if (users.isEmpty()) {
			user.setErrorCode("100");
			user.setErrorMessage("No User is available yet");
			users.add(user);
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} else {
			user.setErrorCode("200");
			user.setErrorMessage("Successfully Fetched the list of the users");

			session.setAttribute("loggedInUserId", user.getEmailId());
			session.setAttribute("loggedInUserRole", user.getRole());
		}
		log.debug("UserController==> Ending getAllUsers method ");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<User> validateCredentials(@RequestBody User user) {
		log.debug("UserController==> Starting validate Credentials method ");
		user = userDAO.isValidUser(user.getEmailId(), user.getPassword());

		if (user == null) {
			user = new User();
			user.setErrorCode("404");
			user.setErrorMessage("Invalid credentials found");

		}

		else {
			user.setErrorCode("200");
			user.setErrorMessage("Successfully logged in as a User");
		}
		log.debug("UserController==> Ending validateCredentials method ");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@PostMapping("/createNewUser")
	public ResponseEntity<User> createNewUser(@RequestBody User user) {

		log.debug("UserController==> Starting CreateNewUser method ");
		if (userDAO.getById(user.getEmailId()) == null) {
			user.setStatus("Valid user");
			user.setReason("Reason1");
			user.setIsOnline("Y");

			if (userDAO.SaveUser(user) == true) {
				user.setErrorCode("200");
				user.setErrorMessage("Registration is successful...");
			} else {
				user.setErrorCode("404");
				user.setErrorMessage("Registration is not successfully...Please try again");
			}
		}

		log.debug("UserController==> Ending createNewUser method ");
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@PutMapping("/UpdateUser")
	public ResponseEntity<User> UpdateExistingUser(@RequestBody User user) {
		log.debug("UserController==> Starting UpdateExistingUser method ");
		if (userDAO.UpdateUser(user)) {
			user.setErrorCode("404");
			user.setErrorMessage("Update is not successful");
		} else {
			user.setErrorCode("202");
			user.setErrorMessage("Update is successful");
		}
		log.debug("UserController==> Ending UpdateExistingUser method ");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/showMyProfile")
	public ResponseEntity<User> myProfile(){
	log.debug("UserController==> Starting myProfile method ");
	 String loggedInUserId= (String) session.getAttribute("loggedInUserId");
	 User user=userDAO.getById(loggedInUserId);
	 if(user==null){
		 user=new User();
		 user.setErrorCode("404");
		 user.setErrorMessage("User does not exists");
		 return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
	 }
	 log.debug("UserController==> Ending myProfile method ");
	return new ResponseEntity<User>(user, HttpStatus.OK);
	 
	}

	@GetMapping("/Logout")
	public ResponseEntity<User> Logout() {
		log.debug("UserController==> Starting logout method ");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		System.out.println(loggedInUserId);
		user.setIsOnline("N");
		user.setIsOffline(loggedInUserId);
		session.invalidate();
		user.setErrorCode("200");
		user.setErrorMessage("You have been successfully LoggedOut");
		log.debug("UserController==> Ending logout method ");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/makeAdmin", method=RequestMethod.PUT)
	public ResponseEntity<User> makeAdmin(@PathVariable("emailId")String emailId)
	{
		log.debug("UserController==> Starting makeAdmin method ");
		user=userDAO.getById(emailId);
		if(user==null)
		{
			user= new User();
			user.setErrorCode("404");
			user.setErrorMessage("User Does Not present with the UserID:-  "+emailId);
		}
		else {
			user.setRole("admin");
			user.setStatus("A");
			userDAO.UpdateUser(user);
			user.setErrorCode("200");
			user.setErrorMessage("User :- "+emailId+" Role has been successfully updated");
		}
		log.debug("UserController==> Ending makeAdmin method ");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}  
	
	@RequestMapping(value="/getAllFriends", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllFriends()
	{
		log.debug("UserController==> Starting getAllFriends method ");
		String loggedInUserId= (String) session.getAttribute("loggedInUserId");
		System.out.println("Calling getAllFriends : " +loggedInUserId);
		
	//List<User> users=new ArrayList<User>();
		List<User> users= userDAO.getAllList(loggedInUserId);
		if (users==null && users.isEmpty()) {
			users.add(user);
			user.setErrorCode("404");
			user.setErrorMessage("No user is available");
			
			log.debug("UserController==> Ending getAllFriends method ");
		}
		else {
			user.setErrorCode("202");
			user.setErrorMessage("User list is fetched successfully");
		}
		log.debug("UserController==> Ending getAllFriends method ");
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
}
