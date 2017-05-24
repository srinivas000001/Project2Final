package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.UserDAO;
import com.niit.collaborationPlatform.model.User;
import com.sun.org.apache.regexp.internal.recompile;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	public User user;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean SaveUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
		} catch (Exception e) {
		}
		return false;
	}

	@Transactional
	public boolean UpdateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
		} catch (Exception e) {
		}
		return false;
	}

	@Transactional
	public boolean DeleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
		} catch (Exception e) {

		}
		return false;
	}

	@Transactional
	public User getById(String emailId) {
		return (User) sessionFactory.getCurrentSession().get(User.class, emailId);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> list() {
		String hql = "From User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<User> getAllList(String emailId) {
		String hql = "select emailId from usertable where emailId in (select emailId from usertable where emailId != ? minus ("
				+ "select friendEmailId from friend where emailId = ? union select emailId from friend where friendEmailId= ?))";
		Query query= sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, emailId);
		query.setString(1,emailId);
        query.setString(2, emailId);
		List<User> users= query.list();
        return users;
	}

	@Transactional
	public void setOnline(String username) {
		String hql = "UPDATE User SET isOnline = 'Y' where username= '" + username + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Transactional
	public void setOffline(String username) {
		String hql = "UPDATE User SET isOnline = 'N' where username= '" + username + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();

	}

	@Transactional
	public User isValidUser(String emailid, String password) {

		try {
			String hql = "From User where emailid=:emailid and password=:password";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setString("emailid", emailid);
			query.setString("password", password);
			return (User) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
