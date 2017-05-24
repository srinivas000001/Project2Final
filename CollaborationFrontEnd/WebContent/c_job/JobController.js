app.controller('JobController', [ 'JobServices', '$http', '$rootScope',
		'$location', '$scope',
		function(JobServices, $http, $rootScope, $location, $scope) {

			$scope.message = "Message from Job Controller"
			console.log("Starting-->JobController")

			var self = this;
			self.job = {
				id : '',
				title : '',
				qualification : '',
				description : '',
				status : '',
				date_time : ''
			}

			self.jobs = [];
			
			/*self.jobApplication = {
				id : '',
				username : '',
				jobId : '',
				date_applied : '',
				status : '',
				remarks :''
			}
			
			self.jobApplications=[];
			self.myJobApplications=[];*/
			
			
			
			self.fetchAllJobs=function(){
	        	console.log("JobController-->Starting fetchAllJobs function")
	        
	        	JobServices.fetchAllJobs().then
	        	(
	        	function(Response)
	        	{
	        		console.log("JobController-->Ending fetchAllJobs function")
	        		self.jobs=Response;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("jobController-->Ending fetchAllJobs function")
	        		console.log("jobController-->The jobs are not fetched successfully")
	        	}
	        	)
			
			},
			
			/*self.fetchAllJobApplications=function(){
	        	console.log("JobController-->Starting fetchAllJobApplications function")
	        
	        	JobServices.fetchAllJobApplications().then
	        	(
	        	function(d)
	        	{
	        		console.log("JobController-->Ending fetchAllJobApplications function")
	        		self.jobApplications=d;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("jobController-->Ending fetchAllJobApplications function")
	        		console.log("jobController-->The job applications are fetched successfully")
	        	}
	        	)
			
			},*/
			
			
			self.jobById=function(id){
	        	console.log("jobController-->Starting jobById function")
	        
	        	JobServices.jobById(id).then
	        	(
	        	function(d)
	        	{
	        		console.log("jobController-->Ending jobById function")
	        		self.jobs=d;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("jobController-->Ending jobById function")
	        		console.log("jobController-->The job with id is not fetched successfully")
	        	}
	        	)
			
			},
			
			
			self.createNewJob=function(job){
				console.log("jobController-->Starting CreateNewJob function")
				
				JobServices.createNewJob(job).then(
				function(d)
				{
					console.log("jobController-->Ending CreateNewJob function")
					alert("The Job is posted successfully")
				},
				
				function(errResponse)
				{
					console.log("jobController ==> Ending createNewJob function()")

					console.log("Error while posting job,, please try again after sometime")
				}
				)
				
			},
			
			self.submit =function()
			{
				console.log("jobController ==> Starting submit function()")

				self.createNewJob(self.job);
				self.reset();
				console.log("jobController ==> Ending submit function()")

		};
			
			self.updateJob=function(){
				console.log("jobController-->Starting UpdateJob function")
				
				JobServices.updateJob(job).then(
				function(d)
				{
					console.log("jobController-->Ending updateJob function")
					alert("The Job is updated successfully with id " +id)
				},
				
				function(errResponse)
				{
					console.log("jobController ==> Ending updateJob function()")

					console.log("Error while updating job,, please try again after sometime")
				}
				)
				
			};
			
			
			
			self.applyForJob = applyForJob
			function applyForJob(jobId)
			{
				console.log("JobController--> Starting applyForJob function")
	            
				console.log("Logged in with id " +$rootScope.currentUser)
				var currentUser=$rootScope.currentUser;
				if(typeof currentUser == 'undefined')
					{
					alert("Please Login to continue")
					console.log("User has not logged in. So cannot apply")
					$location.path('/login')	
					return;
					}
				JobServices.applyForJob(jobId).then(
				function(d){
					alert("Applied the Job successfully")
					console.log("JobController==> Ending applyForJob function with success")
				},
			    function(errResponse)
			    {
					console.log("JobController==> Ending applyforJob function with errors ")
					console.log("JobController==> Ending applyForJob function with errors")
			    }
				)
					
			}
			
			
			self.myAppliedJobs =function()
			{
				console.log("JobController ==> Starting myAppliedJobs function")
				JobServices.myAppliedJobs().then
				(
						function(d)
						{
							self.myJobApplications = d;
							console.log("JobController ==> Ending myAppliedJobs function()")
						},
						function(errResponse)
						{
							console.log("JobController ==> Ending myAppliedJobs function()")

							console.log("Error While fetching myAppliedJobs,.,Please try again after sometime,.,!!,.,!!,.,")
						}
				)

			}
			
			/*self.selectJobApplication =function(username,jobId,reason)
			{
				console.log("JobController ==> Starting selectJobApplication function")

				console.log("JobController ==> Calling selectJobApplication with "+username+"  "+jobid+"  "+reason)
				JobServices.selectJobApplication(username, jobId,reason).then(
						function(d)
						{
							self.jobApplication = d;
							console.log("JobController ==> Ending selectJobApplication function()")
							alert("Selected for the job successfully")
							self.fetchAllJobApplications()
		                    $location.path('/alljobapplication')
						},
						function(errResponse)
						{
							console.log("JobController ==> Ending selectJobApplication function()")
							console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
						}
				
				)
				
			},
			
			self.rejectJobApplication = function(username,jobId,reason)
			{
				console.log("JobController ==> Starting rejectJobApplication function()")

				console.log("JobController ==> Calling rejectJobApplication with "+username+"  "+jobId+"  "+reason)

				JobServices.rejectJobApplication(username,jobId,reason).then
				(
						function(d)
						{
							self.jobapplication = d;
							console.log("JobController ==> Ending rejectJobApplication function()")
							alert("Job Application is rejected successfully ")
							self.fetchAllJobApplications()
							$location.path('/c_admin/adminHome')
						},
						function(errResponse)
						{
							console.log("JobController ==> Ending rejectJobApplication function()")

							console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
						}
				)

		},
		
		self.callForInterview = function(username,jobId,reason)
		{
			console.log("JobController ==> Starting callForInterview function()")

			console.log("JobController ==> Calling callForInterview with "+username+"  "+jobId+"  "+reason)

			JobServices.callForInterview(username,jobiId,reason).then
			(
					function(d)
					{
						self.jobapplication = d;
						console.log("JobController ==> Ending callForInterview function()")
						alert("Successfully Called for the Interview")
						self.fetchAllJobApplications()
						$location.path('/c_admin/adminHome')
					},
					function(errResponse)
					{
						console.log("JobController ==> Ending callForInterview function()")

						console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
					}
			)

	},*/
			
			

			self.fetchAllJobs();
			
			/*self.fetchAllJobApplications();*/
			
			self.reset=function()
			{
				self.job ={
						
						title:'',
						qualification : '',
						description : ''
						
				}
			};
		} ])