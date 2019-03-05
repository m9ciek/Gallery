package com.maciek.gallery.controller;

import com.maciek.gallery.entity.Image;
import com.maciek.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private ImageService imageService;

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir")+ "/uploads";

    @Autowired
    public UploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping
    public String UploadPage(){
        return "upload";
    }

    @PostMapping("/status")
    public ModelAndView upload(@RequestParam("files") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView();

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setPath(UPLOAD_DIRECTORY);

        try {
            imageService.uploadImage(file, image);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("uploadStatus");
            return modelAndView;
        }

        modelAndView.addObject("image", image);
        modelAndView.setViewName("uploadStatus");
        return modelAndView;
    }

}
