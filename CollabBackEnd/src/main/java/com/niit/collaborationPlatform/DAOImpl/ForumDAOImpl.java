package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.ForumDAO;
import com.niit.collaborationPlatform.model.Forum;

@Repository
public class ForumDAOImpl implements ForumDAO {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	public ForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean SaveForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean DeleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean UpdateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public Forum getById(String id) {
		return (Forum) sessionFactory.getCurrentSession().get(Forum.class, id);
	}

	@Transactional
	public List<Forum> list() {
	String hql="From Forum";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}
