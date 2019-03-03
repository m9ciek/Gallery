package com.maciek.gallery.service;

import com.maciek.gallery.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void uploadImage(MultipartFile file, Image image) throws Exception;
    List<Image> findImages();
    void deleteImage(int imageId);

}
