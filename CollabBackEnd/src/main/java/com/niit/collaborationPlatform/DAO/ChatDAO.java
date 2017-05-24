package com.niit.collaborationPlatform.DAO;import java.util.List;

import com.niit.collaborationPlatform.model.Chat;

public interface ChatDAO {

	public boolean SaveChat(Chat chat);
	
	public boolean DeleteChat(Chat chat);
	
	public boolean UpdateChat(Chat chat);
	
	public Chat getid(int id);
	
	public Chat getById(int id);
	
	public List<Chat> list();
	

}
