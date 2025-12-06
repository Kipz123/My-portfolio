package com.allankaino.portfolio.service;

import com.allankaino.portfolio.model.Blog;
import com.allankaino.portfolio.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
