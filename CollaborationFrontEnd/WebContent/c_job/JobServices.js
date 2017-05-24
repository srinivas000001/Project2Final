'use strict';
 
app.service('JobServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->JobService")
	
	var BASE_URL='http://localhost:8091/CollabBackEnd/'
		
    return {
         fetchAllJobs:function(){
        	console.log("Starting--> fetchAllJobs function"); 
        	 return $http.get(BASE_URL+'/getAllJobs').then(
        	function(Response){
        		console.log("Ending-->fetchAllJobs Function");
        		return Response.data;	
        	},
        	null
        	 )
         },
         
        /* fetchAllJobApplications:function(){
        	 console.log("Starting--> fetchAllJobApplications function")
        	 return $http.get(BASE_URL+'/getAllJobApplications').then(
        	function(Response){
        		console.log("Ending-->fetchAllJobApplications function")
        		return Response.data;
        	}	 
        	 )
         },*/
            
            createNewJob: function(job){
            	console.log("Starting-->CreateNewJob function")
            	return $http.post(BASE_URL+'/createNewJob',job).then(
            	function(Response){
            		console.log("Ending-->CreateNewJob Function");
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("The job is not posted. Ending the CreateNewJob function ");
            		return $q.reject(errResponse);
            	}
            	)       	
            },
            
            jobById: function(id){
            	console.log("Starting--> jobById Function")
            	return $http.get(BASE_URL+'/getJobById' +id).then(
            			function(Response){
            				console.log("Ending-->jobById function");;
            				return Response.data;
            			},
            			function(errResponse){
            				console.log("Ending-->jobById function");
            				return $q.reject(errResponse);
            			}
            			)	
            },
            
            updateJob: function(){
            	console.log("Starting--> updateJob function")
            	return $http.get(BASE_URL+'/UpdateJob',job).then(
            	function(Response){
            		console.log("Ending--> updateJob Function with success");
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("Ending-->updateJob Function with errors");
            		return $sq.reject(errResponse);
            	}
            	)
            },
            /*$http.post(BASE_URL+"/applyForJob/"+jobID)*/
            
            applyForJob: function(jobId) {
            	console.log("Starting applyForJob function")
            	return $http.post(BASE_URL+"/applyForJob",jobId).then(
            	function(Response){
            		console.log("Ending applyForJob function");
            		return Response.data;
            	},		
            	
            	function(errResponse){
            		console.log("Ending applyForJob function with errors");
            		return $q.reject(errResponse);
            	}
            	)
            	
            },
            
            /*selectJobApplication: function(username, jobId, reason) {
				console.log("Starting selectJobApplication function")
				return $http.put(BASE_URL+'/selectedJobApplication/'+username +'/'+jobId + '/' + reason).then(
				
						function(Response)
						{
							console.log("Ending selectJobApplication function with success")
							return Response.data
						},
						function(errResponse)
						{
							console.log("Ending selectJobApplication function with error")
							return $q.reject(errResponse);
						}
						
				)
			},
				
            rejectJobApplication: function(username, jobId, reason){
                return $http.put(BASE_URL+'/rejectJobApplication/' +username +'/'+jobId + '/' + reason)
                        .then(
                                function(response){
                                	console.log("Successfully called rejected the job application")
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.log("Error while rejecting job application");
                                    return $q.reject(errResponse);
                                }
                        )
        },
                         
            callForInterview: function(username, jobId, reason){
            	  return $http.put(BASE_URL+'/callForInterview/'+username +'/'+jobId + '/' + reason)
                            .then(
                                    function(response){
                                    	console.log("Successfully called for interiew")
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while calling for interview');
                                        return $q.reject(errResponse);
                                    }
                            )
            },
            */
          
            
            myAppliedJobs :function()
            {
            	console.log("Starting myAppliedJobs function")
            	return $http.get(BASE_URL+'/getMyAppliedJobs').then(
            		
            	function(response)	{
            	console.log("Ending myAppliedJobs function with success");
            	return response.data;
            },
            function(errResponse)
            {
            	console.log("Ending myAppliedJobs function with errors");
            	return $q.reject(errResponse);
            }
            	)
            }
     
    }
              
 
}]);