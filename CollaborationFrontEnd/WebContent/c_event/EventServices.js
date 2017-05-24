'use strict';
 
app.service('EventServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->EventServices")
	
	var BASE_URL='http://localhost:8091/CollabBackEnd/'
		
    return {
         fetchAllEvents:function(){
        	console.log("Starting--> fetchAllEvents function") 
        	 return $http.get(BASE_URL+'/getAllEvents').then(
        	function(Response){
        		console.log("Ending-->fetchAllEvents Function")
        		return Response.data;	
        	},
        	null
        	 )
         },
         
            
            SaveNewEvent: function(event){
            	console.log("Starting-->SaveNewEvent function")
            	return $http.post(BASE_URL+'/createNewEvent',event).then(
            	function(Response){
            		console.log("Ending-->SaveNewEvent Function")
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("The event is not created. Ending the SaveNewEvent function ")
            		return $q.reject(errResponse);
            	}
            	)       	
            },
            
            
            eventById: function(){
            	console.log("Starting--> eventById Function")
            	return $http.get(BASE_URL+'/getEventByID' +id).then(
            			function(Response){
            				console.log("Ending-->eventById function with success")
            				return Response.data;
            			},
            			function(errResponse){
            				console.log("Ending-->eventById function with errors")
            				return $q.reject(errResponse);
            			}
            			)	
            },        
	}
    
            
           
 
}]);