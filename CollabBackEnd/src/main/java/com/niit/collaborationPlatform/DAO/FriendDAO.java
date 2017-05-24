package com.niit.collaborationPlatform.DAO;

import java.util.List;

import com.niit.collaborationPlatform.model.Friend;

public interface FriendDAO {

	public Friend getByid(int id);
	
	public boolean SaveFriend(Friend friend);
	
	public boolean DeleteFriend(Friend friend);
	
	public boolean UpdateFriend(Friend friend);
	
	public List<Friend> getMyFriends(String emailId);
	
	public List<Friend> getMyFriendRequests(String emailId);
	
	public Friend get(String emailId, String friendEmailId);
	
	public void setOnline(String emailId);
	
	public void setOffline(String emailId);
	
	public List<Friend> getMySentFriendRequests(String emailId);
	
	public Integer maxID();
	
	
	
	
	
	
}
