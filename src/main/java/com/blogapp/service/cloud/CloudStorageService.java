package com.blogapp.service.cloud;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public interface CloudStorageService {
 Map<Object,Object> uploadImage(File file, Map<Object,Object> imageProperties) throws IOException;
 Map<Object,Object> uploadImage(MultipartFile multipartFile,Map<Object, Object> imageProperties) throws IOException;
}
