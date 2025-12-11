package com.tastytown.backend.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;


public interface IImageService {
    String uploadFoodImage(MultipartFile file) throws IOException;

    void deleteFoodImage(String imagePublicId) throws IOException;
}
