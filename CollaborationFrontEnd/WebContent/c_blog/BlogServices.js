'use strict';
 
app.service('BlogServices', ['$http','$q', '$rootScope', function($http,$q,$rootScope){
	
	console.log("Starting-->BlogService")
	
	var BASE_URL='http://localhost:8091/CollabBackEnd/'
		
    return {
         fetchAllBlogs:function(){
        	console.log("Starting--> fetchAllBlogs function") 
        	 return $http.get(BASE_URL+'/getAllBlogs').then(
        	function(Response){
        		console.log("Ending-->getAllBlog Function")
        		return Response.data;	
        	},
        	null
        	 )
         },
         
            
            SaveNewBlog: function(blog){
            	console.log("Starting-->SaveNewBlog function")
            	return $http.post(BASE_URL+'/SaveNewBlog',blog).then(
            	function(Response){
            		console.log("Ending-->SaveNewBlog Function")
            		return Response.data;
            	},
            	function(errResponse){
            		console.log("The blog is not posted. Ending the SaveNewBlog function ")
            		return $q.reject(errResponse);
            	}
            	)       	
            },
            
            
            blogById: function(id){
            	console.log("Starting--> blogById Function")
            	return $http.get(BASE_URL+'/getBlogById' +id).then(
            			function(Response){
            				console.log("Ending-->blogById function")
            				//$location.path("/viewSelectedBlog");
            				return Response.data;
            			},
            			function(errResponse){
            				console.log("Ending-->blogById function")
            				return $q.reject(errResponse);
            			}
            			)	
            },        
	}
    
            
           
 
}]);