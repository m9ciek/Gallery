package com.maciek.gallery.service;

import com.maciek.gallery.dao.ImageRepository;
import com.maciek.gallery.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public String uploadImage(MultipartFile[] files, Image image, String uploadDirectory) throws Exception {
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file : files){
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        Image tempImage = imageRepository.find(image.getFileName());
        if(tempImage == null){
            imageRepository.save(image);
        }else {
            throw new RuntimeException("Image already exists"); //Need to create own exception and handle it
        }
        return fileNames.toString();
    }

}
