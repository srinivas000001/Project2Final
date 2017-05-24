package com.niit.collaborationPlatform.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaborationPlatform.DAO.EventDAO;
import com.niit.collaborationPlatform.model.Event;

@RestController
public class EventController {

	@Autowired
	public Event event;

	@Autowired
	public EventDAO eventDAO;

	@Autowired
	public HttpSession session;

	@GetMapping("/getAllEvents")
	public ResponseEntity<List<Event>> getAllEvents() {
		List<Event> events = eventDAO.list();
		if (events.isEmpty()) {
			event.setErrorCode("404");
			event.setErrorMessage("No Event is available. Please create a event");
		} else {
			event.setErrorCode("202");
			event.setErrorMessage("Successfully Fetched the Events");
		}
		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
	}

	@GetMapping("/getEventByID/{eventID}")
	public ResponseEntity<Event> getEventById(@PathVariable("eventID") int id) {
		event = eventDAO.getEventById(id);
		if (event == null) {
			event.setErrorCode("404");
			event.setErrorMessage("No event is available with this ID");
		}

		else {
			event.setErrorCode("202");
			event.setErrorMessage("The event is available in records with id : " + id);
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@PostMapping("/createNewEvent")
	public ResponseEntity<Event> CreateNewEvent(@RequestBody Event event) {
		Date date_time=new Date();
		event.setDate_time(date_time);
		if (eventDAO.SaveEvent(event) == false) {
			event.setErrorCode("404");
			event.setErrorMessage("No Event is created. Please Try Again");
		}

		else {
			event.setErrorCode("202");
			event.setErrorMessage("Event is created successfully");
		}
		return null;

	}

	@PutMapping("/UpdateEvent/{id}")
	public ResponseEntity<Event> UpdateEvent(@RequestBody Event event) {
		
		
		if (eventDAO.SaveEvent(event) == false) {
			event.setErrorCode("404");
			event.setErrorMessage("No Event is updated. Please Try Again");
		}

		else {
			event.setErrorCode("202");
			event.setErrorMessage("Event is updated successfully");
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);

	}

	@PutMapping("/DeleteEvent/{id}")
	public ResponseEntity<Event> DeleteEvent(@RequestBody Event event) {
		if (eventDAO.DeleteEvent(event) == false) {
			event.setErrorCode("404");
			event.setErrorMessage("Event is not deleted.Please try again");
		} else {
			event.setErrorCode("202");
			event.setErrorMessage("Event is Deleted successfully");
		}
		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

}
