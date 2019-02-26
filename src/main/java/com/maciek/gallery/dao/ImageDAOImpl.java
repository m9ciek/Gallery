package com.maciek.gallery.dao;

import com.maciek.gallery.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageDAOImpl implements ImageDAO{

    @Autowired
    private ImageRepository imageRepository;

    private static String uploadDirectory = System.getProperty("user.dir")+ "/uploads";

    @Override
    public String uploadImage(MultipartFile[] files) throws Exception{
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file : files){
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        return fileNames.toString();
    }

    @Override
    public void save(Image image){
        imageRepository.save(image);
    }
}
