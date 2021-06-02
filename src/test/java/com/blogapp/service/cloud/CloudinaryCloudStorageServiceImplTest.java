package com.blogapp.service.cloud;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
            cloudStorageServiceImpl.uploadImage(params);
        }catch (IOException e){
            log.info("Error occurred -->{}",e.getMessage());
        }
    }
}