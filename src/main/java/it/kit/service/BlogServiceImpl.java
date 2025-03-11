package it.kit.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.kit.binding.BlogForm;
import it.kit.entity.BlogEntity;
import it.kit.exception.BlogNotFoundException;
import it.kit.repository.BlogRepository;

@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogRepository blogRepo;
	
	@Override
	public String createBlog(BlogForm Blog) {
		// TODO Auto-generated method stub
		BlogEntity master = new BlogEntity();
		BeanUtils.copyProperties(Blog, master);		
		BlogEntity saveBlog = blogRepo.save(master);
		return  saveBlog != null? "blog is registered" : "Problem is blog  registration";
	}

	  @Override
		public BlogEntity updateBlog(BlogEntity blog, Integer Id) {
		    return blogRepo.findById(Id).map(existingBlog -> {
		        existingBlog.setName(blog.getName());
		        return blogRepo.save(existingBlog);
		    }).orElseThrow(() -> new BlogNotFoundException("Sorry, this blog could not be found"));
		}

	@Override
	public String deleteBlogById(Integer blogid) {
		Optional<BlogEntity>opt= blogRepo.findById(blogid);
		if(opt.isPresent()) {
			blogRepo.deleteById(blogid);
			return "blog is deleted";
		}
		 throw new BlogNotFoundException("blog not found with ID: ");
	}
	
    @Override
    public Page<BlogEntity> getAllBlogs(Pageable pageable) {       
        return blogRepo.findAll(pageable);
    }
		
}
