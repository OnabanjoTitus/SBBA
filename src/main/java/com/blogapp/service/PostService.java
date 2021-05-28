package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.web.dto.PostDto;

import java.util.List;

public interface PostService {
    Post savePost(PostDto postDto);

    List<Post> findAllPost();

    Post updatePost(PostDto postDto);

    Post findById(Integer id);

    void deletePostById(Integer id);

    Post addCommentToPost(Integer id, Comment comment);

}