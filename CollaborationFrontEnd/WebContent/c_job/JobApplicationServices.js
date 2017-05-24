'use strict';
 
app.service('JobApplicationServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->JobApplicationService")
	
	var BASE_URL='http://localhost:8091/CollabBackEnd/'
		
    return {
      
         fetchAllJobApplications:function(){
        	 console.log("Starting--> fetchAllJobApplications function")
        	 return $http.get(BASE_URL+'/getAllJobApplications').then(
        	function(Response){
        		console.log("Ending-->fetchAllJobApplications function")
        		return Response.data;
        	}	 
        	 )
         },
            
            selectJobApplication: function(username, jobId, reason) {
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
            
          
            
            
     
	}
              
 
}]);