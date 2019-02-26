package com.maciek.gallery.service;

import com.maciek.gallery.dao.ImageDAO;
import com.maciek.gallery.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public String uploadImage(MultipartFile[] files, Image image) throws Exception {
        String savedFilesNames;
        savedFilesNames = imageDAO.uploadImage(files);
        imageDAO.save(image);
        return savedFilesNames;
    }
}
