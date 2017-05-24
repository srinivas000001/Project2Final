package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.FriendDAO;
import com.niit.collaborationPlatform.model.Friend;

@Repository
public class FriendDAOImpl implements FriendDAO {

	@Autowired
	SessionFactory sessionFactory;

	public FriendDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean SaveFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().save(friend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean DeleteFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean UpdateFriend(Friend friend) {
		try {
			sessionFactory.getCurrentSession().update(friend);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public List<Friend> getMyFriendRequests(String emailId) {
		String hql = "select friendUserName From Friend where username= '" + emailId + "' and status ='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}
		

	@Transactional
	public List<Friend> getMySentFriendRequests(String emailId) {
		String hql = "select friendUserName From Friend where username= '" + emailId + "' and status ='N'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	

	@Transactional
	public Friend get(String emailId, String friendEmailId) {
		String hql = "From Friend where username = '" + emailId + "' and friendUserName = '" + friendEmailId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Friend) query.uniqueResult();
	}

	@Transactional
	public void setOnline(String emailId) {
		String hql = "UPDATE Friend SET isOnline = 'Y' where username= '" + emailId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();

	}

	@Transactional
	public void setOffline(String emailId) {
		String hql = "UPDATE Friend SET isOnline = 'N' where username= '" + emailId + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Transactional
	public Integer maxID() {
		Integer maxID=100;
		String hql="Select max(id) from Friend";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		try {
			maxID=(Integer) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return maxID;
		}
		return maxID+1;
		
	}

	@Transactional
	public Friend getByid(int id) {
		return (Friend) sessionFactory.getCurrentSession().get(Friend.class, id);
	}

	@Transactional
	public List<Friend> getMyFriends(String emailId) {
		String hql1 = "Select friendUserName from Friend where username = '" + emailId + "' and status ='A'";
		String hql2 = "Select username as FN From Friend Where friendUserName = '" + emailId+ "' and status = 'A'";
		Query query1 = sessionFactory.getCurrentSession().createQuery(hql1);
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Friend> myFriends1 = query1.list();
		List<Friend> myFriends2 = query2.list();
		myFriends1.addAll(myFriends2);

		return myFriends1;
	}
	
}
