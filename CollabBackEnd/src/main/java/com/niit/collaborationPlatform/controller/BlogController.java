package com.niit.collaborationPlatform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationPlatform.DAO.BlogDAO;
import com.niit.collaborationPlatform.model.Blog;

@RestController
public class BlogController {

	@Autowired
	public BlogDAO blogDAO;

	@Autowired
	public Blog blog;

	@Autowired
	public HttpSession session;

	@GetMapping("/Hello")
	public String Hello() {
		return "Hello from Collaboration platform";

	}

	// To get the complete list of the blogs
	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs() {

		List<Blog> blogs = blogDAO.list();
		if (blogs.isEmpty()) {
			blog.setErrorCode("100");
			blog.setErrorMessage("No Blog is available yet");
			blogs.add(blog);
			return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);

		} else {
			blog.setErrorCode("200");
			blog.setErrorMessage("Successfully fetched the list of the blogs");
		}
		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);
	}
	

	@GetMapping("/getBlogById{blogID}")
	public ResponseEntity<Blog> getBlogById(@PathVariable("blogID") int id) {
		blog = blogDAO.getByid(id);
		
		if (blog == null) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No Blog is available with blog ID : " + id);
			
		} 
		
		else {
			blog.setErrorCode("202");
			blog.setErrorMessage("The Blog is available in records with ID : " + id);
		}

		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/SaveNewBlog")
	public Blog SaveNewBlog(@RequestBody Blog blog) {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		if (loggedInUserId==null) {
			blog.setErrorMessage("You have to login to create the blog");
			blog.setErrorCode("404");
			return blog;
		}

		blog.setId(blogDAO.maxID());
		Date date_time=new Date();
		blog.setDate_time(date_time);
		blog.setStatus("Not Approved");
		blog.setReason("New Blog");
		blog.setEmailid(loggedInUserId);
		if (blogDAO.SaveBlog(blog)) {
			blog.setErrorCode("200");
			blog.setErrorMessage("Successfully Created the Blog");
			return blog;
		} else {
			blog.setErrorCode("404");
			blog.setErrorMessage("Blog is not created");
			return blog;
		}
	}
	
	
	

	@PutMapping("/approveBlog/{blogID}")
	public Blog approveBlog(@PathVariable("blogID") int id) {
		blog = blogDAO.getByid(id);

		// User or admin
		if (session.getAttribute("loggedInUserId") == null) {
			blog.setErrorCode("404");
			blog.setErrorMessage("Please Login to approve the blog");
		}

		
		 /**//** if(session.getAttribute("LoggedInUserRole")).equals("Admin") {
		 * blog.setErrorCode("404"); blog.setErrorMessage(
		 * "Please Login to approve the blog"); }
		 *//*
*/
		// if the blog id is not present in th DB
		if (blog == null) {
			blog = new Blog();
			blog.setErrorCode("404");
			blog.setErrorMessage("No blog exits with this id :" + id);
			return blog;
		}

		// if the blog is already approved
		if (blog.getStatus().equals("Approved")) {
			blog.setErrorCode("404");
			blog.setErrorCode("This blog is already approved :" + id);
			return blog;
		}

		blog.setStatus("Approved");
		if (blogDAO.UpdateBlog(blog)) {
			blog.setErrorCode("200");
			blog.setErrorMessage("Successfully Approved the Blog");
		}

		else {
			blog.setErrorCode("404");
			blog.setErrorMessage("Not able to update the blog");
		}

		return blog;

	}

	
	
	
		
	
}
