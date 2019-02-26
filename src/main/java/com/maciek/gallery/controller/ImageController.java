package com.maciek.gallery.controller;

import com.maciek.gallery.entity.Image;
import com.maciek.gallery.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/upload")
public class ImageController {

    private ImageService imageService;

    private static String uploadDirectory = System.getProperty("user.dir")+ "/uploads";

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping("")
    public String UploadPage(){
        return "upload";
    }

    @PostMapping("/status")
    public String upload(Model model, @RequestParam("files") MultipartFile[] files){

        String fileNames = null;

        for(MultipartFile file : files){
            Image image = new Image();
            image.setFileName(file.getOriginalFilename());
            image.setPath(uploadDirectory);
            try {
                fileNames=imageService.uploadImage(files, image, uploadDirectory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("msg", "Succesfully uploaded files " + fileNames + " ");
        return "uploadStatus";
    }

}
