package com.blogapp.service;

import com.blogapp.data.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    @Mock
    PostRepository postRepository;

    @InjectMocks
    PostService postServiceimpl = new PostServiceImpl();
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
}