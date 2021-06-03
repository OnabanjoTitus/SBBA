package com.blogapp.service.cloud;

import com.blogapp.web.dto.PostDto;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CloudinaryCloudStorageServiceImplTest {
    @Autowired
    CloudStorageService cloudStorageServiceImpl;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void testImage(){
        File file= new File("C:\\Users\\DELL\\Desktop\\937259.jpg");
        assertThat(file.exists()).isTrue();
        Map<Object,Object> params= new HashMap<>();
        params.put("file",file);
        try {
            cloudStorageServiceImpl.uploadImage(file,params);
        }catch (IOException e){
            log.info("Error occurred -->{}",e.getMessage());
        }
    }
    @Test
    void uploadMultipartImageTest() throws IOException {
        PostDto postDto= new PostDto();
        postDto.setTitle("Title");
        postDto.setContent("Test");
        Path path= Paths.get("C:\\Users\\DELL\\Desktop\\937259.jpg");
        MultipartFile multipartFile= new MockMultipartFile("937259.jpg","937259.jpg", "img/jpg",Files.readAllBytes(path));
        log.info("Multipart object created --> {}",multipartFile);
        assertThat(multipartFile).isNotNull();
        postDto.setCoverImageUrl(multipartFile);

        log.info("File name -->{}",postDto.getCoverImageUrl().getOriginalFilename());

        cloudStorageServiceImpl.uploadImage(multipartFile, ObjectUtils.asMap("public_id","blogapp/"+extractFileName(Objects.requireNonNull
                (postDto.getCoverImageUrl().getOriginalFilename()))));
        assertThat(postDto.getCoverImageUrl().getOriginalFilename()).isEqualTo("937259.jpg");
    }

    private String extractFileName(String fileName) {
        return fileName.split("\\.")[0];
    }
}