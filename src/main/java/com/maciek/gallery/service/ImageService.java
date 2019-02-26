package com.maciek.gallery.service;

import com.maciek.gallery.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile[] files, Image image, String uploadDirectory) throws Exception;
}
