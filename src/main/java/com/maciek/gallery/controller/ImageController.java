package com.maciek.gallery.controller;

import com.maciek.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {

    ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("/content")
    public String initializePage(Model model){
        model.addAttribute("images", imageService.findImages());
        return "content";
    }

    @GetMapping("/images")
    public String retrieveImages(Model model){
        model.addAttribute("images", imageService.findImages());
        return "images";
    }

    @GetMapping("/images/delete")
    public String deleteImage(@RequestParam("imageId") int imageId){
        imageService.deleteImage(imageId);
        return "redirect:/images";
    }

}
