package com.blogapp.service;

import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postServiceImpl = new PostServiceImpl();
    Post testPost;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
       testPost= new Post();
    }


    @Test
    void whenTheSaveMethodIsCalled_thenRepositoryIsCalledOnceTest() throws PostObjectIsNullException {
        when(postServiceImpl.savePost(new PostDto())).thenReturn(testPost);
        postServiceImpl.savePost(new PostDto());

        verify(postRepository,times(1)).save(testPost);
    }
    @Test
    void whenTheFindAllMethodIsCalled_thenReturnAListOfPost(){
        List<Post> postList= new ArrayList<>();
        when(postServiceImpl.findAllPost()).thenReturn(postList);
        postServiceImpl.findAllPost();
        verify(postRepository,times(1));
    }
}