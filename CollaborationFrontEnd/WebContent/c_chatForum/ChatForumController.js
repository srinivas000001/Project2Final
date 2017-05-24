"use strict";

app.controller('ChatForumController', function(ChatForumServices, $scope){
	
console.log("Starting ChatForumController")

$scope.chatMessage= "Message from ChatForum Controller";

$scope.messages = [];

$scope.message ="";

$scope.max = 50;

$scope.addMessage = function()
{
	console.log("ChatForumController ==> Starting addMessage function()")
	
	ChatForumServices.send($scope.message);
	
	$scope.message ="";
	
	console.log("ChatForumController ==> Ending addMessage function()")
}

   ChatForumServices.recieve().then(null,null,function(message){
	
	   console.log("ChatForumController ==> Starting recieve function()")
	   
	   $scope.messages.push(message);
	   
	   console.log("ChatForumController ==> Ending recieve function()")
   })
	
})