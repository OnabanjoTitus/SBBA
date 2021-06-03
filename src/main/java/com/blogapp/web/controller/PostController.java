package com.blogapp.web.controller;

import com.blogapp.data.models.Post;
import com.blogapp.service.PostService;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostNotFoundException;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping("/fullPost/{postId}")
    public String getPostById(@PathVariable("postId") Integer id,Model model) throws PostNotFoundException {
        log.info("Request for a post path -->{}",id);
      Post post= postServiceImpl.findById(id);
        model.addAttribute("post",post);
        return "post";
    }

    @PostMapping("/save")
    public String savePost(@ModelAttribute @Valid PostDto postDto, BindingResult result, Model model){

        log.info("Post dto received-->{}",postDto);
        if(result.hasErrors()){
            return "create";
        }
        try{
            postServiceImpl.savePost(postDto);
        } catch (PostObjectIsNullException e) {
          log.info("Exception occurred -->{}",e.getMessage());
        }catch(DataIntegrityViolationException dx){
            model.addAttribute("error",dx.getMessage());
            model.addAttribute("errorMessage",dx.getMessage());
            model.addAttribute("postDto",postDto);  
            return "create";
        }
        return "redirect:/posts/";
    }
}
