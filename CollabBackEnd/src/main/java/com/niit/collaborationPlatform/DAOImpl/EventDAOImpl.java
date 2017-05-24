package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.EventDAO;
import com.niit.collaborationPlatform.model.Event;

@Repository
public class EventDAOImpl implements EventDAO {

	@Autowired
	public EventDAO eventDAO;

	@Autowired
	public Event event;

	@Autowired
	public SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean SaveEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().save(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Transactional
	public boolean UpdateEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Transactional
	public boolean DeleteEvent(Event event) {
		try {
			sessionFactory.getCurrentSession().delete(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public Event getEventById(int id) {
		return (Event) sessionFactory.getCurrentSession().get(Event.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Event> list() {
		String hql="From Event";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
