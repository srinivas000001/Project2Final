'use strict';
app.controller('UserController', [ '$scope', 'UserServices', '$location',
		'$rootScope', '$http', '$cookieStore',
		function($scope, UserServices, $location,$rootScope, $http, $cookieStore) {

			//$scope.message("Message from User controller");
	$scope.message="Message from User Controller";
			console.log("Starting UserController")

			var self = this;

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
			self.friendusers=[];
			
			self.fetchAllUsers=function(){
	        	console.log("UserController-->Starting fetchAllUsers function")
	        
	        	UserServices.fetchAllUsers().then
	        	(
	        	function(d)
	        	{
	        		console.log("UserController-->Ending fetchAllUsers function")
	        		self.users=d;
	        	},
	        	function(errResponse)
	        	{
	        		console.log("UserController-->Ending fetchAllUsers function")
	        		console.log("UserController-->The users are not fetched successfully")
	        	}
	        	)
			
			},
			
			
			
			self.createNewUser=function(user){
				console.log("UserController-->Starting CreateNewUser function")
				
				UserServices.createNewUser(user).then(
				function(d)
				{
					console.log("UserController-->Ending CreateNewUser function")
					alert("Thanks for your Registration.You have registered successfully")
				},
				
				function(errResponse)
				{
					console.log("UserController ==> Ending createNewUser function()")

					console.log("Error while creating user, please try again after sometime")
				}
				)
				
			},
			
			self.submit=function()
			{
				console.log("UserController ==> Starting submit user function()")

				self.createNewUser(self.user);
				console.log("UserController ==> Ending submit function()")

		};
			
			self.updateUser=function(){
				console.log("UserController-->Starting UpdateUser function")
				
				UserServices.updateUser(user).then(
				function(d)
				{
					console.log("UserController-->Ending updateJob function")
					alert("The User is updated successfully with id " +id)
				},
				
				function(errResponse)
				{
					console.log("UserController ==> Ending updateUser function()")

					console.log("Error while updating user, please try again after sometime")
				}
				)
				
			};
			
			self.updateUser=function() {
				{
				self.updateUser(self.user);
				}
				},
			
				
				//to authenticate 
			self.authenticate = function(user)
			{
				console.log("UserController ==> Starting authenticate function()")
				UserServices.authenticate(user).then
				(
						function(d)
						{
							self.user = d;
							if(self.user.errorCode =="404")
								{
									alert(self.user.errorMessage)
									self.emailId=''
									self.password=''
										console.log("UserController ==> Ending authenticate function()")
									
								}
							else
								{
									$rootScope.currentUser = self.user
									$cookieStore.put("currentUser",self.user)
									$rootScope.emailId = self.user.emailId
									console.log("Logging in with Email :- "+$rootScope.currentUser.emailId)
									$rootScope.IsLoggedIn="true"
									if($rootScope.currentUser.role==='Admin')
										{
												$rootScope.isAdmin="true"
												console.log("UserController ==> Login as "+$rootScope.currentUser.role)
												console.log("UserController ==> Ending authenticate function with admin()")
												$location.path('/adminhome')
										}
									else
										{
										        $rootScope.showProfile='true'
										        console.log("UserController ==> Login as "+$rootScope.currentUser.role)
												console.log("UserController ==> Ending authenticate function with user()")
												$location.path('/userHome')	
										}
								}
						}
				)
}
				
				//login function called
				self.login=function(){
					{
					console.log("Login validation started", self.user)
					self.authenticate(self.user);
					console.log("Login validation ended")
				}
				};
				
				
				self.myProfile=function(){
					UserServices.myProfile().then
					(
							function(d)
							{
								self.user=d;
								console.log("Successfully fetched the profile of the user: " +$rootScope.currentUser.username)
							},
							function(errResponse)
							{
								console.error("Error While Fetching Profile,.,..,.")
							}
)
				}
				
				self.makeAdmin = function(emailId){
					console.log("UserController ==> Starting makeAdmin function()")
					console.log("UserController ==> Calling MakeAdmin Function with username "+emailId)
					UserServices.makeAdmin(emailId).then
					(
							function(d)
							{
								self.user =  d;
								console.log("UserController ==> Ending makeAdmin function()")
								alert(self.user.errorMessage)
								$location.path('/allusers')
							}											)
}
				
			//logout function	
			self.logoutUser=function(){
				$rootscope.currentUser={}
				$rootscope.isAdmin="false"
				$cookieStore.remove("currentUser")
				UserServices.logoutUser().then(
				
						function(d)
						{
							$rootscope.IsLoggedIn="false"
								self.user = d;
							alert(self.user.errorMessage)
                           $location.path('/');
						}
						
				)
			}
			
			//login function called
			self.logoutUser=function(){
				{
				console.log("Logged out function started", self.user)
				self.logoutUser();
				console.log("Successfully Loggedout ")
				$location.path('/');
			}
			};
			
			
			self.fetchAllUsers();
			
			
			//Reset function
			self.reset = function(){
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
};
				}])