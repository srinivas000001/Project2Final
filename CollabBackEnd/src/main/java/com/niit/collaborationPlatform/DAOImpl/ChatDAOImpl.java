package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.ChatDAO;
import com.niit.collaborationPlatform.model.Chat;

@Repository
public class ChatDAOImpl implements ChatDAO {

	@Autowired
	public SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean SaveChat(Chat chat) {

		try {
			sessionFactory.getCurrentSession().save(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean DeleteChat(Chat chat) {
		try {
			sessionFactory.getCurrentSession().delete(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean UpdateChat(Chat chat) {
		try {
			sessionFactory.getCurrentSession().update(chat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Chat> list() {
		String hql="From Chat";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public Chat getid(int id) {
		
		return (Chat) sessionFactory.getCurrentSession().get(Chat.class, id );
	}

	@Transactional
	public Chat getById(int id) {
		
		return (Chat) sessionFactory.getCurrentSession().get(Chat.class, id);
	}

}
