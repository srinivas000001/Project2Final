package com.niit.collaborationPlatform.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaborationPlatform.DAO.BlogDAO;
import com.niit.collaborationPlatform.model.Blog;

@SuppressWarnings("deprecation")
@Repository
public class BlogDAOImpl implements BlogDAO {
	
	
	@Autowired
	public SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public boolean SaveBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean UpdateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean DeleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional
	public Blog getid(int id) {
		
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	}

	@Transactional
	public Blog getByid(int id) {
	
		return (Blog) sessionFactory.getCurrentSession().get(Blog.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
		public List<Blog> list() {
			String hql = "from Blog";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();

		
	}

	@Transactional
	public Integer maxID() {
		
		Integer maxID=100;
		try {
			String hql="Select max(id) from Blog";
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
			maxID=(Integer) query.uniqueResult();
		} catch (Exception e) {
			maxID=100;
			e.printStackTrace();
		}
		
		return maxID+1;
	}

}
