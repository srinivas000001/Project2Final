'use strict';
 
app.service('UserServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->UserServices")
	
	var BASE_URL='http://localhost:8091/CollabBackEnd/'
		
    return {
         fetchAllUsers:function(){
        	console.log("Starting--> fetchAllUsers function") 
        	 return $http.get(BASE_URL+'/getAllUsers').then(
        	function(Response){
        		console.log("Ending-->fetchAllUsers function")
        		return Response.data;	
        	},
        	null
        	 )
         },
            
            createNewUser: function(user){
            	console.log("Starting-->CreateNewUser function")
            	return $http.post(BASE_URL+'/createNewUser', user).then(
            	function(Response){
            		console.log("Ending-->CreateNewUser Function")
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("The user is not created. Ending the CreateNewUser function ")
            		return $q.reject(errResponse);
            	}
            	)       	
            },
            
            updateUser: function(){
            	console.log("Starting--> updateUser function")
            	return $http.put(BASE_URL+'/UpdateUser',user).then(
            	function(Response){
            		console.log("Ending--> updateUser Function with success")
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("Ending-->updateUser Function with errors")
            		return $sq.reject(errResponse);
            	}
            	)
            },
       
             
            authenticate: function(user){
            	console.log("Starting-->login function")
                    return $http.post(BASE_URL+'/login',user)
                            .then(
                                    function(response){
                                    	console.log("Ending-->loginUser function with success")
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Ending-->loginUser function with errors');
                                        return $q.reject(errResponse);
                                    }
                            )
            },
             
            logoutUser: function(){
            	console.log("Starting-->logout function")
                    return $http.put(BASE_URL+'/Logout')
                            .then(
                                    function(response){
                                    	console.log("Ending-->loginUser function with success")
                                        return response.data;
                                    }
                                   
                            )
            },
            
     
            myProfile: function(){
            	console.log("Starting==> My profile function")
            	return $http.get(BASE_URL+'/showMyProfile')
            	.then(
            			function(response){
            				console.log("Ending==> My profile function")
            				return response.data;
            			}
            			)
            },
            
            makeAdmin:function(username)
            {
            	console.log("Starting==> makeAdmin function")
            	return $http.put(BASE_URL +'/makeAdmin/' +username)
            	.then(
            	
            			function(Response){
            				console.log("Ending==> makeAdmin function with success")
            				return Response.data;
            			},
            			function(errResponse){
            				console.error('Ending-->makeAdmin function with errors');
                            return $q.reject(errResponse);
            			}
            			
            	)
            	
            	
            },
            
            getAllFriend:function()
            {
            	console.log("Starting==> getAllFriends function")
            	return $http.get(BASE_URL + '/getAllFriends')
            	.then(
            			function(Response){
            				console.log("Ending==> getAllFriends function with success")
            				return Response.data;
            			},
            			function(errResponse){
            				console.error('Ending-->getAllFriends function with errors');
                            return $q.reject(errResponse);
            			}		
            	)
            	
            	
            }
            
    }
        
 
}]);