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
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationPlatform.DAO.JobDAO;
import com.niit.collaborationPlatform.model.Job;
import com.niit.collaborationPlatform.model.JobApplication;

@RestController
public class JobController {

	@Autowired
	public Job job;

	@Autowired
	public JobDAO jobDAO;

	@Autowired
	public JobApplication jobApplication;

	@Autowired
	public HttpSession session;

	@GetMapping("/getAllJobs")
	public ResponseEntity<List<Job>> getAllJobs() {
		List<Job> jobs = jobDAO.list();
		if (jobs.isEmpty()) {
			job.setErrorCode("404");
			job.setErrorMessage("No Job is available yet. Please Create a Job");
		}

		else {
			job.setErrorCode("202");
			job.setErrorMessage("Successfully Fetched the job list");
		}

		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);
	}

	@GetMapping("getAllOpenJobs")
	public ResponseEntity<List<Job>> getAllOpenJobs() {
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		List<Job> jobs = jobDAO.getJobs(loggedInUserId);
		if (jobs == null) {
			job.setErrorCode("404");
			job.setErrorMessage("No Jobs are available of this id");
		}

		else {
			job.setErrorCode("202");
			job.setErrorMessage("Successfully Fetched the jobs of this id");
		}
		return new ResponseEntity<List<Job>>(jobs, HttpStatus.OK);

	}

	@GetMapping("/getJobById/{jobID}")
	public ResponseEntity<Job> getJobById(@PathVariable("jobID") int id) {
		job = jobDAO.getByid(id);
		if (job == null) {
			job = new Job();
			job.setErrorCode("404");
			job.setErrorMessage("No Job is available with this ID : " + id);
		} else {
			job.setErrorCode("202");
			job.setErrorMessage("The Job is available in records with ID : " + id);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	@PostMapping("/createNewJob")
	public ResponseEntity<Job> createNewJob(@RequestBody Job job) {
		job.setId(jobDAO.maxID());
		Date date_time = new Date();
		job.setDate_time(date_time);
		job.setStatus("N");

		if (jobDAO.SaveJob(job) == false) {
			job.setErrorCode("404");
			job.setErrorMessage("Error in creating job is not created");
			
		} else {
			job.setErrorCode("202");
			job.setErrorMessage("Successfully created the job");
		}
		
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	
	@PutMapping("UpdateJob")
	public ResponseEntity<Job> UpdateJob(@RequestBody Job job)
	{
		if (jobDAO.UpdateJob(job)==false) {
			job.setErrorCode("404");
			job.setErrorMessage("Error while updating the Job. Please try again");
		}
		
		else {
			job.setErrorCode("202");
			job.setErrorMessage("Job is successfully created");
		}
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
	
	
	
}
