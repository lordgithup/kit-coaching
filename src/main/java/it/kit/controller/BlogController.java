package it.kit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.kit.binding.BlogForm;
import it.kit.entity.BlogEntity;
import it.kit.entity.EventEntity;
import it.kit.service.BlogService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Blog-api")
@CrossOrigin("http://localhost:3000")
public class BlogController {


	@Autowired
	private BlogService blogService;
	
	@PostMapping("/saveBlog")
	public ResponseEntity<String> saveBlog(@RequestBody @Valid BlogForm blog){		
		try {
			//user service
			 String resultMsg= blogService.createBlog(blog);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	  
	@PutMapping("/update/{id}")
		    public ResponseEntity<BlogEntity> updateBlog(@RequestBody BlogEntity blog, @PathVariable("id") Integer id) {
		        try {
		            BlogEntity updatedBlog = blogService.updateBlog(blog, id);
		            return ResponseEntity.ok(updatedBlog);
		        } catch (Exception e) {
		            e.printStackTrace();
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }

	

	@DeleteMapping("/deleteBlog/{id}")
	public ResponseEntity<String> deleteBlogById(@PathVariable Integer id){		
		try {
			//user service
			 String resultMsg= blogService.deleteBlogById(id);
			 return new ResponseEntity<String>(resultMsg,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@GetMapping("/Blogs")
    public ResponseEntity<Map<String, Object>> getEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "blogid") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "") String search) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                    ? Sort.by(sortBy).ascending() 
                    : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<BlogEntity> BlogPage = blogService.getAllBlogs(pageable);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("data", BlogPage.getContent());
        response.put("currentPage", BlogPage.getNumber());
        response.put("totalItems",BlogPage.getTotalElements());
        response.put("totalPages", BlogPage.getTotalPages());
        return ResponseEntity.ok(response);
    }
	
	
}





