package com.tastytown.backend.service;

import java.io.IOException;

public interface IImageService {
    String uploadFoodImage(MultipartFile file) throws IOException;

    void deleteFoodImage(String imagePublicId) throws IOException;
}
