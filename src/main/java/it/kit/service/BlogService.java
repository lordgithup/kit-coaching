package it.kit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import it.kit.binding.BlogForm;
import it.kit.entity.BlogEntity;


public interface BlogService {

	
	public String createBlog(BlogForm Blog);
	
	public BlogEntity updateBlog(BlogEntity Blog, Integer  id);

	public String deleteBlogById(Integer  blogid);
	
	public Page<BlogEntity> getAllBlogs(Pageable pageable);
	
	
}
