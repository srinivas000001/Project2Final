package com.niit.collaborationPlatform.DAO;

import java.util.List;

import com.niit.collaborationPlatform.model.Forum;

public interface ForumDAO {

	public boolean SaveForum(Forum forum);
	
	public boolean DeleteForum(Forum forum);
	
	public boolean UpdateForum(Forum forum);
	
	public Forum getById(String id);
	
	public List<Forum> list();
}
