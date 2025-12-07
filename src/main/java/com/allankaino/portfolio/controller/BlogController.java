package com.allankaino.portfolio.controller;

import com.allankaino.portfolio.model.Blog;
import com.allankaino.portfolio.service.BlogService;
import com.allankaino.portfolio.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }

    @PostMapping
    public Blog createBlog(@RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("author") String author,
            @RequestParam("image") MultipartFile image) {

        String fileName = fileStorageService.storeFile(image);
        // Store the web-accessible path
        String imagePath = "/uploads/" + fileName;

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setAuthor(author);
        blog.setImagePath(imagePath);

        return blogService.createBlog(blog);
    }
}
