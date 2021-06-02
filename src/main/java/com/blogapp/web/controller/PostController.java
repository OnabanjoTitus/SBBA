package com.blogapp.web.controller;

import com.blogapp.data.models.Post;
import com.blogapp.service.PostService;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postServiceImpl;

    @GetMapping("")
    public String getIndex(Model model){
        List<Post> postList= postServiceImpl.findAllPost();
        model.addAttribute("postList",postList);
        return "index";
    }
    @GetMapping("/create")
    public String getPostForm(Model model){
        model.addAttribute("postDto", new PostDto());
        return "create";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDto postDto){

        log.info("Post dto received-->{}",postDto );
        try{
            postServiceImpl.savePost(postDto);
        } catch (PostObjectIsNullException e) {
          log.info("Exception occurred -->{}",e.getMessage());
        }
        return "index";
    }
}
