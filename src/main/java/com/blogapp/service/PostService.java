package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostNotFoundException;
import com.blogapp.web.exceptions.PostObjectIsNullException;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Post savePost(PostDto postDto) throws PostObjectIsNullException;

    List<Post> findAllPost();

    Post updatePost(PostDto postDto);

    Post findById(Integer id) throws PostNotFoundException;

    void deletePostById(Integer id);

    Post addCommentToPost(Integer id, Comment comment);

}
