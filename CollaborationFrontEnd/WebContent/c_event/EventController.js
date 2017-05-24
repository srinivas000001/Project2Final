'use strict';

app.controller('EventController', ['EventServices', '$http', '$rootScope',
		'$location', '$scope',
		function(EventServices, $http, $rootScope, $location, $scope) {

			$scope.message = "Message from Event Controller"
			console.log("Starting-->EventController")

			var self = this;
			self.event = {
				id : '',
				name : '',
				venue :'',
			    description : '',
				date_time : ''		
			}

			self.events = [];
			
			
			var currentUser=$rootScope.currentUser;
			
			//to get all the blogs
			self.fetchAllEvents=function(){
	        	console.log("EventController-->Starting fetchAllEvents function")
	        
	        	EventServices.fetchAllEvents().then
	        	(
	        	function(Response)
	        	{
	        		console.log("EventController-->Ending fetchAllEvents function")
	        		self.events=Response;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("EventController-->Ending fetchAllEvents function")
	        		console.log("EventController-->The events are not fetched successfully")
	        	}
	        	)
			
			},
			
			//to get blog with id
			self.eventById=function(){
	        	console.log("EventController-->Starting eventById function")
	        
	        	EventServices.eventById(id).then
	        	(
	        	function(d)
	        	{
	        		console.log("EventController-->Ending eventById function with success")
	        		self.blog=d;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("EventController-->Ending eventById function")
	        		console.log("EventController-->The event is not fetched successfully")
	        	}
	        	)
			
			},
			
			
			//to create new blog
			self.SaveNewEvent=function(event){
				console.log("EventController-->Starting SaveNewEvent function")
				
				EventServices.SaveNewEvent(event).then(
				function(d)
				{
					console.log("EventController-->Ending SaveNewEvent function")
					alert("The Event is posted successfully")
				},
				
				function(errResponse)
				{
					alert("The Eveny was not posted. Please try again after sometime")
					console.log("EventController ==> Ending SaveNewEvent function()")

					console.log("Error while posting event, please try again after sometime")
				}
				)
				
			},
			
			self.submit =function()
			{
				console.log("EventController ==> Starting event submit function()")

				self.SaveNewEvent(self.event);
				self.reset();
				console.log("EventController ==> Ending event submit function()")

		};
			
		self.fetchAllEvents();
		
		self.reset=function()
		{
			self.event ={
					
					id :'',
					name : '',
					venue : '',
					description : ''
					
			}
		};
		
			

		} ])
 