package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.JobDAO;
import com.niit.collaborationPlatform.model.Job;

@Repository
public class JobDAOImpl implements JobDAO {

	@Autowired
	public SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {

		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean SaveJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean DeleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean UpdateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public Job getByid(int id) {
		
		return (Job) sessionFactory.getCurrentSession().get(Job.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Job> getJobs(String username) {
	String hql="From Job";
	Query query=sessionFactory.getCurrentSession().createQuery(hql);	
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Job> getOpenJobs() {
		String hql="From Job";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public Integer maxID() {
		Integer maxID=100;
		try {
			String hql="Select max(id) from job";
			@SuppressWarnings("unused")
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
		} catch (Exception e) {
			maxID=100;
			e.printStackTrace();
		}
		return maxID+1;
	}

	@Transactional
	public List<Job> list() {
		String hql="From Job";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

}


