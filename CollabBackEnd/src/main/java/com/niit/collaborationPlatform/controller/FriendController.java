package com.niit.collaborationPlatform.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.collaborationPlatform.DAO.FriendDAO;
import com.niit.collaborationPlatform.DAO.UserDAO;
import com.niit.collaborationPlatform.model.Friend;

public class FriendController {

	@Autowired
	public Friend friend;

	@Autowired
	public FriendDAO friendDAO;

	@Autowired
	public UserDAO userDAO;

	@Autowired
	public HttpSession session;

	public static Logger log = org.slf4j.LoggerFactory.getLogger(FriendController.class);

	@RequestMapping(value = "/getMyFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getFriendRequests(HttpSession session) {
		log.debug("FriendController==> Starting getFriendRequests Method");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		List<Friend> friendRequest = new ArrayList<Friend>();
		if (loggedInUserId == null) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue");
			friendRequest.add(friend);
			log.debug("FriendController==> Ending getFriendRequests Method");
		}
		log.debug("FriendController ====> Searching friends for " + loggedInUserId);
		friendRequest = friendDAO.getMyFriendRequests(loggedInUserId);

		if (friendRequest.isEmpty()) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently no friend requests are available");
			friendRequest.add(friend);
			log.debug("FriendController==> Ending getFriendRequests Method");
		}
		return new ResponseEntity<List<Friend>>(friendRequest, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMySentFriendRequests", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getSentFriendRequests(HttpSession session) {
		log.debug("FriendController==> Starting getSentFriendRequests Method");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		List<Friend> sentFriendRequest = new ArrayList<Friend>();
		if (loggedInUserId == null) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue");
			sentFriendRequest.add(friend);
			log.debug("FriendController==> Ending getSentFriendRequests Method");
		}
		sentFriendRequest = friendDAO.getMySentFriendRequests(loggedInUserId);
		if (sentFriendRequest.isEmpty()) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently no friend requests are available");
			sentFriendRequest.add(friend);
			log.debug("FriendController==> Ending getSentFriendRequests Method");
		}
		return new ResponseEntity<List<Friend>>(sentFriendRequest, HttpStatus.OK);

	}

	@RequestMapping(value = "/getMyFriends", method = RequestMethod.GET)
	public ResponseEntity<List<Friend>> getMyFriends(HttpSession session) {
		log.debug("FriendController==> Starting getMyFriends method");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");

		List<Friend> myFriends = new ArrayList<Friend>();
		if (loggedInUserId == null) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue");
			myFriends.add(friend);
			log.debug("FriendController==> Ending getSentFriendRequests Method");
		}

		myFriends = friendDAO.getMyFriends(loggedInUserId);
		if (myFriends.isEmpty()) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Currently no friend requests are available");
			myFriends.add(friend);
			log.debug("FriendController==> Ending getSentFriendRequests Method");
		}
		return new ResponseEntity<List<Friend>>(myFriends, HttpStatus.OK);
	}

	@RequestMapping(value = "/sendFriendRequest/{friendEmailId}", method = RequestMethod.GET)
	public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendEmailId") String friendEmailId) {
		log.debug("FriendController==> Starting sendFriendRequests method");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		if (loggedInUserId == null) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue");
			log.debug("FriendController==> Ending sendFriendRequests method");
		} else {
			friend.setId(friendDAO.maxID());
			friend.setStatus('N');
			friend.setIsOnline('N');
			friend.setEmailId(loggedInUserId);
			friend.setFriendEmailId(friendEmailId);
			if (friendDAO.get(loggedInUserId, friendEmailId) != null) {
				friend.setErrorCode("404");
				friend.setErrorMessage("You have already sent the Friend Request to " + friendEmailId);
				log.debug("FriendController==> Ending sendFriendRequests method");
			} else {
				friend.setErrorCode("404");
				friend.setErrorMessage("Friend Request is sent successfully");
				log.debug("FriendController==> Ending sendFriendRequests method");
			}
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);

	}

	@RequestMapping(value = "/acceptFriendRequest/{friendEmailId}")
	public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendEmailId") String friendEmailId) {
		log.debug("FriendController==> Starting acceptFriendRequest method");
		friend = acceptOrRejectFriendRequest(friendEmailId, 'A');
		log.debug("FriendController==> Ending acceptFriendRequest method");
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/rejectFriendRequest/{friendEmailId}")
	public ResponseEntity<Friend> rejectFriendRequest(@PathVariable("friendEmailId") String friendEmailId) {
		log.debug("FriendController==> Starting rejectFriendRequest method");
		friend = acceptOrRejectFriendRequest(friendEmailId, 'R');
		log.debug("FriendController==> Ending rejectFriendRequest method");
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	@RequestMapping(value = "/deleteFriend/{friendEmailId}")
	public ResponseEntity<Friend> deleteFriend(@PathVariable("friendEmailId") String friendEmailId) {
		log.debug("FriendController==> Starting rejectFriendRequest method");

		Friend friend = (Friend) friendDAO.getMyFriendRequests(friendEmailId);

		if (friendDAO.DeleteFriend(friend)) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Error while deleting friend. Please try again after sometime");
		} else {
			friend.setErrorCode("202");
			friend.setErrorMessage("Friend is deleted successfully");
		}
		return new ResponseEntity<Friend>(friend, HttpStatus.OK);
	}

	public Friend acceptOrRejectFriendRequest(String friendEmailId, char status) {
		log.debug("FriendController==> Starting acceptOrRejectFriendRequest method");
		String loggedInUserId = (String) session.getAttribute("loggedInUserId");
		if (loggedInUserId == null) {
			friend.setErrorCode("404");
			friend.setErrorMessage("Please Login to Continue");
			log.debug("FriendController==> Ending acceptOrRejectFriendRequest method");
		} else {
			friend = friendDAO.get(friendEmailId, loggedInUserId);
			friend.setStatus(status);
			friendDAO.UpdateFriend(friend);
			friend.setErrorCode("202");
			friend.setErrorMessage("Operation was successful");
			log.debug("FriendController ====> Ending of the acceptOrRejectFriendRequest method()");

		}
		return friend;
	}

}
