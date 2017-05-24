package com.niit.collaborationPlatform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.collaborationPlatform.DAO.ForumDAO;
import com.niit.collaborationPlatform.model.Forum;

public class ForumController {

	@Autowired
	public Forum forum;

	@Autowired
	public ForumDAO forumDAO;
	
	public Logger log= LoggerFactory.getLogger(ForumController.class);

	// get list of all forums
	@RequestMapping("/getAllForums")
	public ResponseEntity<List<Forum>> getAllForums() {
		log.debug("Starting the method getAllForums");
		List<Forum> forums = forumDAO.list();
		if (forums.isEmpty()) {
			forum.setErrorCode("404");
			forum.setErrorMessage("No Forums were found");
			forums.add(forum);
		}
		log.debug("Ending the method getAllForums");
		return new ResponseEntity<List<Forum>>(forums, HttpStatus.OK);
	}

	@RequestMapping("/forumById/{id}")
	public ResponseEntity<Forum> getForumById(@PathVariable("id")String id)
	{
		log.debug("Starting the method getForumById");
		forum=forumDAO.getById(id);
		if(forum==null)
		{
			forum=new Forum();
			forum.setErrorCode("404");
			forum.setErrorMessage("No Forums were found");
		}
		log.debug("Ending the method getForumById");
		return new ResponseEntity<Forum>(forum, HttpStatus.OK);
	}
	
	
	@RequestMapping("/saveForum")
	public ResponseEntity<Forum> saveForum(@RequestBody Forum forum)
	{
		log.debug("Starting the method saveForum");
		if(forumDAO.SaveForum(forum))
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("Error in creating forum. Please try again");
		}
		else {
			forum.setErrorCode("404");
			forum.setErrorMessage("Thank you Forum is created successfully");
		}
		log.debug("Ending the method saveForum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
	@RequestMapping("/updateForum")
	public ResponseEntity<Forum> updateForum(@RequestBody Forum forum)
	{
		log.debug("Starting the method updateForum");
		if(forumDAO.UpdateForum(forum))
		{
			forum.setErrorCode("404");
			forum.setErrorMessage("Error in updating forum. Please try again");
		}
		else {
			forum.setErrorCode("404");
			forum.setErrorMessage("Thank you Forum is updated successfully");
		}
		log.debug("Ending the method updateForum");
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
	}
	
}
