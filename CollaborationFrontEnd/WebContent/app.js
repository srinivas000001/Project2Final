var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'c_common/Home.html'
		
	})
	
	.when('/login', {
		templateUrl : 'c_user/login.html',
			controller : 'UserController'		
	})
	
	.when('/Logout', {
		templateUrl : 'c_common/Home.html',
			controller : 'UserController'		
	})
	
	.when('/Register',{
		templateUrl :'c_user/Register.html',
			controller : 'UserController'
	})
	
	
	//admin related mapping
	.when('/adminhome',{
		templateUrl:'c_admin/adminHome.html',
			controller : 'UserController'
	})
	
	.when('/allJobApplications',{
		templateUrl:'c_admin/allJobApplications.html',
			controller : 'JobController'
	})
	
	//user related mapping
	.when('/UserList',{
		templateUrl:'c_admin/UserList.html',
			controller : 'UserController'
	})
	
	/*.when('/allJobApplications',{
		templateUrl:'c_admin/allJobApplications.html',
			controller : 'JobController'
	})*/
	
	//admin related mapping
	.when('/userHome',{
		templateUrl:'c_user/userHome.html'
	})
	
	.when('/myProfile',{
		templateUrl:'c_user/MyProfile.html',
			controller : 'UserController'
	})
	
	.when('/Home', {
		redirectTo :'/'
		
	})
	
	//blog related mapping
	.when('/CreateBlog', {
		templateUrl : 'c_blog/CreateBlog.html',
		controller :'BlogController'
		
	})
	
	.when('/AllBlogs', {
		templateUrl : 'c_blog/AllBlogs.html',
		controller :'BlogController'
		
	})
	
	.when('/ViewSelectedBlog', {
		templateUrl : 'c_blog/ViewSelectedBlog.html',
		controller :'BlogController'
		
	})
	
	
	//event related mapping
	.when('/CreateEvent', {
		templateUrl : 'c_event/CreateEvent.html',
		controller :'EventController'
		
	})
	
	.when('/AllEvents', {
		templateUrl : 'c_event/AllEvents.html',
		controller :'EventController'
		
	})
	
	//job related mapping
	.when('/postJob', {
		templateUrl : 'c_job/postJob.html',
		controller :'JobController'
		
	})
	
	
	//friend related mapping
	.when('/allFriends', {
		templateUrl : 'c_friend/allFriends.html',
		controller :'FriendController'
		
	})
	.when('/allFriends', {
		templateUrl : 'c_friend/allFriends.html',
		controller :'FriendController'
		
	})
	
	.when('/myFriends', {
		templateUrl : 'c_friend/myFriends.html',
		controller :'FriendController'
		
	})
	
	
	
	.otherwise({
		redirectTo : '/'
	})
	
		});