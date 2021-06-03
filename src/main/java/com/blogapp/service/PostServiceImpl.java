package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.service.cloud.CloudStorageService;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostNotFoundException;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    CloudStorageService cloudStorageService;

    @Autowired
    PostRepository postRepository;

    private String extractFileName(String fileName){
        return fileName.split("\\.")[0];
    }
    @Override
    public Post savePost(PostDto postDto) throws PostObjectIsNullException {
        if (postDto==null){
            throw new PostObjectIsNullException("Post cannot be null");
        }
        Post post= new Post();
        if(postDto.getCoverImageUrl()!=null && !postDto.getCoverImageUrl().isEmpty()){
            Map<Object,Object> params=new HashMap<>();
            params.put("public_id","blogapp/"+extractFileName(postDto.getCoverImageUrl().getName()));
            params.put("overwrite",true);
            log.info("Image parameters-->{}",params);
            try{
            Map<?,?> uploadResult = cloudStorageService.uploadImage(postDto.getCoverImageUrl(),params);
                post.setCoverImageUrl(String.valueOf(uploadResult.get("url")));
            }catch (IOException e){
             e.printStackTrace();
            }

        }
        post.setContent(postDto.getContent());
        post.setTitle(postDto.getTitle());

//        ModelMapper modelMapper= new ModelMapper();
//        modelMapper.map(postDto,post);
        log.info("Post object after mapping -->{}",post);
        try{
            return postRepository.save(post);
        }catch(DataIntegrityViolationException ex){
            log.info("Exception occurred-->{}",ex.getMessage());
            throw ex;
        }
    }

    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post updatePost(PostDto postDto) {
        return null;
    }

    @Override
    public Post findById(Integer id) throws PostNotFoundException {
        if(id==null){
            throw new NullPointerException("Id cannot be null");
        }
     Optional<Post> post= postRepository.findById(id);
     if(post.isPresent()){
         return post.get();
     }else {
       throw new PostNotFoundException("post with Id -->{}");
     }
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
