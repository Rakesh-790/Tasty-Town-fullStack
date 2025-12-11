package com.tastytown.backend.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tastytown.backend.service.IImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
    private final Cloudinary cloudinary;

    @Override
    public String uploadFoodImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.emptyMap()
        );

        // Cloudinary returns many properties, we care about the URL
        return uploadResult.get("secure_url").toString();
    }

    @Override
    public void deleteFoodImage(String publicId) throws IOException {
        if (publicId == null || publicId.isBlank()) return;

        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
