'use strict';
 
app.service('FriendServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->FriendServices")
	
	var BASE_URL='http://localhost:8091/CollaborationPlatform/'
		
    return {
		//to send the friend request
         sendFriendRequest:function(friendEmailId){
        	console.log("Starting--> sendFriendRequest function") 
        	 return $http.get(BASE_URL+'/sendFriendRequest',friendEmailId ).then(
        	function(Response){
        		console.log("Ending-->sendFriendRequet Function")
        		return Response.data;	
        	},
        	function(errResponse)
        	{
        		console.log("Ending--> sendFriendRequest function()")

        		return $q.reject(errResponse);
        	}
        	 )
         },
         
            //to accept the accept request
            acceptFriendRequest:function(friendEmailId){
            	console.log("Starting--> acceptFriendRequest function") 
           	 return $http.get(BASE_URL+'/acceptFriendRequest',friendEmailId ).then(
           	function(Response){
           		console.log("Ending-->acceptFriendRequet Function")
           		return Response.data;	
           	},
           	function(errResponse)
           	{
           		console.log("Ending--> acceptFriendRequest function()")

           		return $q.reject(errResponse);
           	}
           	 )
            },
            
            
            //to reject the friend request
            rejectFriendRequest:function(friendEmailId){
            	console.log("Starting--> rejectFriendRequest function") 
           	 return $http.get(BASE_URL+'/rejectFriendRequest',friendEmailId ).then(
           	function(Response){
           		console.log("Ending-->rejectFriendRequet Function")
           		return Response.data;	
           	},
           	function(errResponse)
           	{
           		console.log("Ending--> rejectFriendRequest function()")

           		return $q.reject(errResponse);
           	}
           	 )
            },
            
            //to get my friendRequest
            getMyFriendRequest : function()
            {
            	console.log("Starting--> getMyFriendRequest function") 
            	 return $http.get(BASE_URL+'/getMyFriendRequests').then(
            	           	function(Response){
            	           		console.log("Ending-->getMyFriendRequest Function")
            	           		return Response.data;	
            	           	},
            	           	function(errResponse)
            	           	{
            	           		console.log("Ending-->getMyFriendRequest function()")

            	           		return $q.reject(errResponse);
            	           	}
            	           	 )
            	            },
            
            	            //to get my friendRequest
            	            getMySentFriendRequest : function()
            	            {
            	            	console.log("Starting--> getMyFriendRequest function") 
            	            	 return $http.get(BASE_URL+'/getMySentFriendRequests').then(
            	            	           	function(Response){
            	            	           		console.log("Ending-->getSentMyFriendRequest Function")
            	            	           		return Response.data;	
            	            	           	},
            	            	           	function(errResponse)
            	            	           	{
            	            	           		console.log("Ending-->getMySentFriendRequest function()")

            	            	           		return $q.reject(errResponse);
            	            	           	}
            	            	           	 )
            	            	            }, 	            
            	 
            	            	            
            	            	            //to get my friends
            	            	            getMyFriends : function()
            	            	            {
            	            	            	console.log("Starting--> getMyFriend function") 
            	            	            	 return $http.get(BASE_URL+'/getMyFriends').then(
            	            	            	           	function(Response){
            	            	            	           		console.log("Ending-->getMyFriend Function")
            	            	            	           		return Response.data;	
            	            	            	           	},
            	            	            	           	function(errResponse)
            	            	            	           	{
            	            	            	           		console.log("Ending-->getMyFriend function()")

            	            	            	           		return $q.reject(errResponse);
            	            	            	           	}
            	            	            	           	 )
            	            	            	            }, 	              	            
            	            
            	            
            	            
            }
            
            
            
	
    
            
           
 
}]);