package com.blogapp.data.repository;

import com.blogapp.data.models.Author;
import com.blogapp.data.models.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
@Sql(scripts = {"classpath:db/insert.sql"})
class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void savePostToDBTest(){
        Post blogPost=new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("This is fintech");
        log.info("Created a blog post-->{}",blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();

    }
    @Test
    void throwExceptionsWhenSavingPostWithDuplicateTitle(){
        Post blogPost=new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("This is fintech");
        log.info("Created a blog post-->{}",blogPost);
        postRepository.save(blogPost);
        assertThat(blogPost.getId()).isNotNull();


        Post blogPost2=new Post();
        blogPost2.setTitle("What is Fintech?");
        blogPost2.setContent("This is fintech");
        log.info("Created a blog post-->{}",blogPost2);
        assertThrows(DataIntegrityViolationException.class,()->postRepository.save(blogPost2));
    }

    @Test
    void whenPostIsSaveAuthor_IsSaved(){
        Post blogPost=new Post();
        blogPost.setTitle("What is Fintech?");
        blogPost.setContent("This is fintech");
        log.info("Created a blog post-->{}",blogPost);




        Author author= new Author();
        author.setFirstName("John");
        author.setLastName("Wick");
        author.setEmail("john@mail.com");
        author.setPhoneNumber("090783838838");


        //map relationships
        blogPost.setAuthor(author);
        author.addPost(blogPost);

        postRepository.save(blogPost);
        log.info("Created a blog post-->{}",blogPost);
    }
    @Test
    void findAllPostInTheDbTest(){
        List<Post>existingPosts=postRepository.findAll();
        assertThat(existingPosts).isNotNull();
        assertThat(existingPosts).hasSize(5);
    }
    @Test
    @Transactional
    @Rollback(value = false)
    void deletePostTest(){
        Post savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNotNull();
        log.info("Post fetched from database -->{}",savedPost);
        //delete post
        postRepository.deleteById(41);

        savedPost = postRepository.findById(41).orElse(null);
        assertThat(savedPost).isNull();
    }
}