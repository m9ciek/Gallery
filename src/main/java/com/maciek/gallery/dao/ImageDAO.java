package com.maciek.gallery.dao;

import com.maciek.gallery.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDAO {
    String uploadImage(MultipartFile[] files) throws Exception;
    void save(Image image);
}
