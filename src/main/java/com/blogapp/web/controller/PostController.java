package com.blogapp.web.controller;

import com.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public String getIndex(){
        return "index";
    }
    @GetMapping("/post/create")
    public String getPostForm(){
        return "create";
    }

    @PostMapping("/post")
    public String savePost(){

        return null;
    }
}
