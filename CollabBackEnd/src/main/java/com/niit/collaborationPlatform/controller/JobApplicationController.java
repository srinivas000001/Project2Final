package com.niit.collaborationPlatform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationPlatform.DAO.JobApplicationDAO;
import com.niit.collaborationPlatform.model.JobApplication;

import ch.qos.logback.classic.Logger;

@RestController
public class JobApplicationController {

	@Autowired
	public JobApplication jobApplication;

	@Autowired
	public JobApplicationDAO jobApplicationDAO;

	@Autowired
	HttpSession session;

	@RequestMapping("/getAllJobApplications")
	public ResponseEntity<JobApplication> getAllJobApplications() {
		List<JobApplication> jobApplications = jobApplicationDAO.list();
		if (jobApplications.isEmpty()) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorCode("Job applications were not found");
			jobApplications.addAll(jobApplications);
		}

		else {
			jobApplication.setErrorCode("202");
			jobApplication.setErrorMessage("Job applications retrieved successfully");
		}
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	// if the user wants to apply for the job
	//@RequestMapping(value = "/applyForJob/{jobId}", method = RequestMethod.POST)
	@PostMapping("/applyForJob{jobId}")
	public ResponseEntity<JobApplication> applyForJob(@PathVariable("jobId") String jobId, HttpSession session) {
		String username = (String) session.getAttribute("loggedInUserId");
		System.out.println(username);

		// If user has not logged in
		if (username == null) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Please Login to Continue");
		}

		else {
			// if user has logged in but applied for the job or not
			if (jobApplicationDAO.getJobApplication(username, jobId) == null) {
				jobApplication.setJobId(jobId);
				jobApplication.setStatus('N');
				jobApplication.setUsername(username);
				Date date_applied = new Date();
				jobApplication.setDate_applied(date_applied);
				jobApplication.setId(jobApplicationDAO.maxID());

				// if the job application is successful

				if (jobApplicationDAO.SaveJobApplication(jobApplication)) {
					jobApplication.setErrorCode("200");
					jobApplication.setErrorMessage("Applied for the job successfully");
				}

			} else {
				// if user has logged in and to check if already applied for the
				// job
				System.out.println("You have already applied for a job.. !! ..!! ..");
				jobApplication.setErrorCode("404");
				jobApplication.setErrorMessage("You have already applied for a job..");
			}
		}

		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);

	}

	// if the person wants to view the list of applied jobs
	@RequestMapping(value = "/getMyAppliedJobs", method = RequestMethod.GET)
	public ResponseEntity<JobApplication> myAppliedJobs(HttpSession session) {
		String username = (String) session.getAttribute("loggedInUserId");
		List<JobApplication> jobApplications = new ArrayList<JobApplication>();
		if (username == null) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("Please Login to continue to view your applied jobs");
		} else {
			jobApplications = jobApplicationDAO.myAppliedJob(username);
		}
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);

	}

	// if the person is selected for the job
	@RequestMapping(value = "/selectedJobApplication/{username}/{jobId}/{remarks}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> selectJobApplication(@PathVariable("username") String username,
			@PathVariable("jobId") String jobId, @PathVariable("remarks") String remarks) {
		jobApplication = updateJobApplication(username, jobId, 'S', remarks);
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	// if the person is rejected for the job
	@RequestMapping(value = "/rejectJobApplication/{username}/{jobId}/{remarks}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> rejectJobApplication(@PathVariable("username") String username,
			@PathVariable("jobId") String jobId, @PathVariable("remarks") String remarks) {
		jobApplication = updateJobApplication(username, jobId, 'R', remarks);
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	// if the person is to be called for interview
	@RequestMapping(value = "/callForInterview/{username}/{jobId}/{remarks}", method = RequestMethod.PUT)
	public ResponseEntity<JobApplication> callForInterview(@PathVariable("username") String username,
			@PathVariable("jobId") String jobId, @PathVariable("remarks") String remarks) {
		jobApplication = updateJobApplication(username, jobId, 'C', remarks);
		return new ResponseEntity<JobApplication>(jobApplication, HttpStatus.OK);
	}

	// private method to minimize the code
	private JobApplication updateJobApplication(String username, String jobId, char status, String remarks) {
		if (isuserAppliedForJob(username, jobId) == false) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You are not authorised to process this....|||");
			return jobApplication;
		}

		String loggedInUserRole = (String) session.getAttribute("loggedInUserRole");
		if (loggedInUserRole == null || loggedInUserRole.isEmpty()) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You are not authorized to do this....|||");
			return jobApplication;
		}
		if (loggedInUserRole.equals("admin")) {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("You are not authorized to do this....|||");
			return jobApplication;
		}
		jobApplication = jobApplicationDAO.getJobApplication(username, jobId);
		jobApplication.setStatus(status);
		jobApplication.setRemarks(remarks);
		if (jobApplicationDAO.UpdateJobApplication(jobApplication)) {
			jobApplication.setErrorCode("200");
			jobApplication.setErrorMessage("Status has been changed to : " + status);
		} else {
			jobApplication.setErrorCode("404");
			jobApplication.setErrorMessage("We are Sorry.. The status cannot be updated");
		}

		return jobApplication;

	}

	private boolean isuserAppliedForJob(String username, String jobId) {
		if (jobApplicationDAO.getJobApplication(username, jobId) == null) {
			return false;
		} else {
			return true;
		}

	}

}
