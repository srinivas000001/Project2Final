'use strict';

app.controller('FriendController', ['UserServices','FriendServices', '$http', '$rootScope',
		'$location', '$scope',
		function(UserServices, FriendServices, $http, $rootScope, $location, $scope) {

			$scope.message = "Message from Friend Controller"
			console.log("Starting-->FriendController")

			var self = this;
			self.friend = {
				id : '',
				username : '',
				friendUserName :'',
			    status : '',
				isOnline : ''		
			}

			self.friends=[];
			self.myfriends=[];
			
			self.user = {
					username : '',
					emailId : '',
					password : '',
					role : '',
					mobile : '',
					gender : '',
					status : '',
					reason : '',
					isOnline : '',
					errorCode:'',
					errorMessage:''
				}
			
			self.users=[];
			
			
			var currentUser=$rootScope.currentUser;
			
			//to get all the friends
			self.getAllFriend=function(){
	        	console.log("FriendController-->Starting getAllFriend function")
	        
	        	UserServices.getAllFriend().then
	        	(
	        	function(Response)
	        	{
	        		console.log("FriendController-->Ending getAllFriend function")
	        		self.friends=Response;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("FriendController-->Ending getAllFriend function")
	        		console.log("FriendController-->The friends are not fetched successfully")
	        	}
	        	)
			
			},
					
			
			//to send the friend request
			self.sendFriendRequest=function(friendEmailId){
				console.log("FriendController-->Starting sendFriendRequest function")
				console.log(friendUserName)
				
				FriendServices.sendFriendRequest(friendEmailId).then(
						function(d)
						{
							self.friend=d;
							alert(self.friend.errorMessage)
							console.log("FriendController ==> Ending sendFriendRequest function()")
							self.getAllFriend();

						},
						function(errResponse)
						{
							console.error("Error  While Sending Friend Request")
							console.log("FriendController ==> Ending sendFriendRequest function()")
						}		
							)
				
			},
			
			self.send =function(friendEmailId)
			{
				console.log(friendEmailId)
				console.log(self.user.emailId)
				console.log("FriendController ==> Starting send function under sendFriendRequest")
				self.sendFriendRequest(friendEmailId)
                console.log("FriendController ==> Ending send function under sendFriendRequest")
                self.getAllFriend();
				$location.path('/myFriends')
		},
		
		//to accept the friend request
		self.acceptFriendRequest=function(friendEmailId){
			console.log("FriendController-->Starting acceptFriendRequest function")
			console.log("calling acceptFriendRequest function with friend UserName as: "+friendEmailId)
			
			FriendServices.acceptFriendRequest(friendEmailId).then(
					function(d)
					{
						self.friend=d;
						self.friendRequestAccepted="true";
						console.log("FriendController ==> Ending acceptFriendRequest function()")
						alert(self.friend.errorMessage)
						self.getAllFriend();
						$location.path('/viewFriendRequest')

					},
					function(errResponse)
					{
						console.error("Error  While accepting Friend Request")
						console.log("FriendController ==> Ending sendFriendRequest function()")
					}		
						)
			
		},
		
		//to get my friend requests
		self.getMyFriendRequest=function(){
        	console.log("FriendController-->Starting getMyFriendRequest function")
        
        	FriendServices.getMyFriendRequest().then
        	(
        	function(Response)
        	{
        		if(Response!=null)
        			{
        			self.friends=Response;
        			console.log("FriendController-->Ending getMyFriendRequest function")
        			}
        		else {
					$scope.empty="yes"
				}
				
        	},
        	function(errResponse)
        	{
        		console.log("FriendController-->Ending getMyFriendRequest function")
            }
        	)
		
		},
		
		self.getMyFriendRequest();
		
		
		self.getMyFriends=function(){
        	console.log("FriendController-->Starting getMyFriends function")
        
        	FriendServices.getMyFriends().then
        	(
        	function(Response)
        	{
        		console.log("FriendController-->Ending getMyFriends function")
        		self.friends=Response;
        	},
        	function(errResponse)
        	{
        		console.log("FriendController-->Ending getMyFriends function")
            }
        	)
		
		},
		
		self.getMyFriends();
		
		self.getAllFriend();
		
		
		
		
			
		/*self.fetchAllfriends();
		
		//self.friendById(id);
		
		self.reset=function()
		{
			self.friend ={
					
					id:'',
					title : '',
					description : ''
					
			}
		};*/
		
			

		} ])
 