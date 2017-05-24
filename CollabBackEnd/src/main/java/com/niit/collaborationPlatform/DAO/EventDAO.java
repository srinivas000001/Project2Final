package com.niit.collaborationPlatform.DAO;

import java.util.List;

import com.niit.collaborationPlatform.model.Event;

public interface EventDAO {
	
	public boolean SaveEvent(Event event);
	
	public boolean UpdateEvent(Event event);
	
	public boolean DeleteEvent(Event event);
	
	public Event getEventById(int id);
	
	public List<Event> list();

}
