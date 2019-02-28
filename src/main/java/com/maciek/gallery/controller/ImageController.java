package com.maciek.gallery.controller;

import com.maciek.gallery.entity.Image;
import com.maciek.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ImageController {

    private ImageService imageService;

    private static String uploadDirectory = System.getProperty("user.dir")+ "/src/main/resources/static/uploads/";

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("/")
    public String mainPage(){
        return "index";
    }


    @RequestMapping("/upload")
    public String UploadPage(){
        return "upload";
    }

    @PostMapping("/status")
    public ModelAndView upload(@RequestParam("files") MultipartFile file){
        ModelAndView modelAndView = new ModelAndView();

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setPath(uploadDirectory);

        modelAndView.addObject("image", image);

        try {
            imageService.uploadImage(file, image);
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("error");
            return modelAndView;
        }

        modelAndView.addObject("image", image);
        modelAndView.setViewName("uploadStatus");
        return modelAndView;
    }

    @GetMapping(value = "/images")
    public String retrieveImages(Model model){
        model.addAttribute("images", imageService.findImages());
        return "images";
    }
}
