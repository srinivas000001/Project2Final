package com.niit.collaborationPlatform.DAO;

import java.util.List;

import com.niit.collaborationPlatform.model.Blog;

public interface BlogDAO {

	public boolean SaveBlog(Blog blog);
	
	public boolean UpdateBlog(Blog blog);
	
	public boolean DeleteBlog(Blog blog);
	
	public Blog getid(int id);
	
	public Blog getByid(int id);
	
	public List<Blog> list();
	
	public Integer maxID();
	
	
}
