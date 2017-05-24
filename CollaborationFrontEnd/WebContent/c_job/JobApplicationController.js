app.controller('JobApplicationController', [ 'JobApplicationServices', '$http', '$rootScope',
		'$location', '$scope',
		function(JobServices, $http, $rootScope, $location, $scope) {

			$scope.message = "Message from JobApplicationController"
			console.log("Starting-->JobApplicationController")

			
			self.jobApplication = {
				id : '',
				username : '',
				jobId : '',
				date_applied : '',
				status : '',
				remarks :''
			}
			
			self.jobApplications=[];
			self.myJobApplications=[];
			
			
			self.fetchAllJobApplications=function(){
	        	console.log("JobApplicationController-->Starting fetchAllJobApplications function")
	        
	        	JobServices.fetchAllJobApplications().then
	        	(
	        	function(d)
	        	{
	        		console.log("JobApplicationController-->Ending fetchAllJobApplications function")
	        		self.jobApplications=d;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("JobApplicationController-->Ending fetchAllJobApplications function")
	        		console.log("JobApplicationController-->The job applications are fetched successfully")
	        	}
	        	)
			
			},
			
			self.selectJobApplication =function(username,jobId,reason)
			{
				console.log("JobApplicationController ==> Starting selectJobApplication function")

				console.log("JobApplicationController ==> Calling selectJobApplication with "+username+"  "+jobid+"  "+reason)
				JobServices.selectJobApplication(username, jobId,reason).then(
						function(d)
						{
							self.jobApplication = d;
							console.log("JobApplicationController ==> Ending selectJobApplication function()")
							alert("Selected for the job successfully")
							self.fetchAllJobApplications()
		                    $location.path('/alljobapplication')
						},
						function(errResponse)
						{
							console.log("JobApplicationController ==> Ending selectJobApplication function()")
							console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
						}
				
				)
				
			},
			
			self.rejectJobApplication = function(username,jobId,reason)
			{
				console.log("JobApplicationController ==> Starting rejectJobApplication function()")

				console.log("JobApplicationController ==> Calling rejectJobApplication with "+username+"  "+jobId+"  "+reason)

				JobServices.rejectJobApplication(username,jobId,reason).then
				(
						function(d)
						{
							self.jobapplication = d;
							console.log("JobApplicationController ==> Ending rejectJobApplication function()")
							alert("Job Application is rejected successfully ")
							self.fetchAllJobApplications()
							$location.path('/c_admin/adminHome')
						},
						function(errResponse)
						{
							console.log("JobApplicationController ==> Ending rejectJobApplication function()")

							console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
						}
				)

		},
		
		self.callForInterview = function(username,jobId,reason)
		{
			console.log("JobApplicationController ==> Starting callForInterview function()")

			console.log("JobApplicationController ==> Calling callForInterview with "+username+"  "+jobId+"  "+reason)

			JobServices.callForInterview(username,jobiId,reason).then
			(
					function(d)
					{
						self.jobapplication = d;
						console.log("JobApplicationController ==> Ending callForInterview function()")
						alert("Successfully Called for the Interview")
						self.fetchAllJobApplications()
						$location.path('/c_admin/adminHome')
					},
					function(errResponse)
					{
						console.log("JobApplicationController ==> Ending callForInterview function()")

						console.log("Error While Updating Job.,.,Please try again after sometime,.,!!,.,!!,.,")
					}
			)

	},
			
			
			
			self.fetchAllJobApplications();
			
			
		} ])