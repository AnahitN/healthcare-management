package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.security.CurrentUser;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${healthcare.upload.image.path}")
    private String imageUploadPath;
    @GetMapping("/")
    public String main(ModelMap modelMap){
        Object currentUserObj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUserObj instanceof CurrentUser){
          CurrentUser currentUser = (CurrentUser) currentUserObj;
          modelMap.addAttribute("user",currentUser.getUser());
        }
        return "index";
    }

    @GetMapping("/getImage")
    public @ResponseBody byte[] getImage ( @RequestParam ("imageName") String imageName) throws IOException {
        File file = new File(imageUploadPath + imageName);
        if (file.exists()){
            FileInputStream fileInputStream = new FileInputStream(file);
            return IOUtils.toByteArray(fileInputStream);
        }
        return null;
    }


}
