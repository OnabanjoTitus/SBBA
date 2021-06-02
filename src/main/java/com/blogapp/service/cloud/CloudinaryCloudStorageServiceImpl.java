package com.blogapp.service.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Service
public class CloudinaryCloudStorageServiceImpl implements CloudStorageService {
    @Autowired
    Cloudinary cloudinary;

    @Override
    public Map<Object, Object> uploadImage(File file, Map<Object, Object> imageProperties) throws IOException {
        return cloudinary.uploader().upload(file,imageProperties);
    }

    @Override
    public Map<Object, Object> uploadImage(MultipartFile multipartFile, Map<Object, Object> imageProperties) throws IOException {
        return cloudinary.uploader().upload(multipartFile.getBytes(),imageProperties);
    }
}
