package com.blogapp.service;

import com.blogapp.data.models.Comment;
import com.blogapp.data.models.Post;
import com.blogapp.data.repository.PostRepository;
import com.blogapp.service.cloud.CloudStorageService;
import com.blogapp.web.dto.PostDto;
import com.blogapp.web.exceptions.PostObjectIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostServiceImpl implements PostService {

    @Autowired
    CloudStorageService cloudStorageService;

    @Autowired
    PostRepository postRepository;


    @Override
    public Post savePost(PostDto postDto) throws PostObjectIsNullException {
        if (postDto==null){
            throw new PostObjectIsNullException("Post cannot be null");
        }
        Post post= new Post();
        if(postDto.getCoverImageUrl()!=null && !postDto.getCoverImageUrl().isEmpty()){
            Map<Object,Object> params=new HashMap<>();
            params.put("public_id","blogapp/"+postDto.getCoverImageUrl().getName());
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
        return postRepository.save(post);
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
    public Post findById(Integer id) {
        return null;
    }

    @Override
    public void deletePostById(Integer id) {

    }

    @Override
    public Post addCommentToPost(Integer id, Comment comment) {
        return null;
    }
}
