package com.maciek.gallery.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadImage(MultipartFile[] files) throws Exception;
}
