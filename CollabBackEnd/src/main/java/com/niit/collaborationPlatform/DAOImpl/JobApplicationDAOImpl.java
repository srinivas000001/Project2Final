package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.JobApplicationDAO;
import com.niit.collaborationPlatform.model.JobApplication;

@Repository
public class JobApplicationDAOImpl implements JobApplicationDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public JobApplicationDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public JobApplication getJobApplication(int id) {
		return (JobApplication) sessionFactory.getCurrentSession().get(JobApplication.class, id);
	}

	@Transactional
	public JobApplication getJobApplication(String username, String jobId) {
		String hql = "FROM JobApplication where username='"+username+"' and JobId = '"+jobId+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return (JobApplication) query.list();
	}

	@Transactional
	public List<JobApplication> list() {
		String hql = "from JobApplication";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public boolean SaveJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().save(jobApplication);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean DeleteJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().delete(jobApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean UpdateJobApplication(JobApplication jobApplication) {
		try {
			sessionFactory.getCurrentSession().update(jobApplication);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public List<JobApplication> myAppliedJob(String username) {
		String hql= "From JobApplication where emailId= '" +username+ "'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public Integer maxID()
	{
		Integer maxId = 100;
		try {
			String hql = "Select max(id) from JobApplication";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxId= (Integer) query.uniqueResult();
		} catch (Exception e) {
			maxId= 100;
			e.printStackTrace();
		}
		return maxId+1;
	}


}
