package com.maciek.gallery.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageServiceImpl implements ImageService {

    private static String uploadDirectory = System.getProperty("user.dir")+ "/uploads";

    @Override
    public String uploadImage(MultipartFile[] files) throws Exception {
        StringBuilder fileNames = new StringBuilder();
        for(MultipartFile file : files){
            Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileNames.append(file.getOriginalFilename());
            Files.write(fileNameAndPath, file.getBytes());
        }
        return fileNames.toString();
    }
}
