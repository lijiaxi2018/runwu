package com.springstarter.controller;

import com.springstarter.service.AccountManager;
import com.springstarter.pojo.User;

import com.springstarter.service.IFileSystemStorage;
import org.springframework.http.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.*;


@RestController
public class LoginController {

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private IFileSystemStorage fileSystemStorage;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/")
    public String index() {
        return "server is started";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/SignIn", method = RequestMethod.GET)
    public String login(String username, String password) {
        User gotUser = accountManager.loginUser(username, password);
        if (gotUser != null) {
            return "200";
        } else {
            return "201";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/SignUp", method = RequestMethod.POST)
    public String register(@RequestBody Map<String, String> registerInfo) {
        if (!accountManager.checkUserName(registerInfo.get("username"))) {
            System.out.println("username has been used!");
            return "201";
        } else if (!accountManager.checkPassword(registerInfo.get("password"))) {
            return "202";
        }
        String username = registerInfo.get("username");
        String password = registerInfo.get("password");
        String organization = registerInfo.get("organization");
        String avatarFilename = registerInfo.get("avatar");
        accountManager.createUser(username, password, organization, avatarFilename);
        System.out.println("User -" + username + "- created successfully!");
        return "200";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/Upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("handling /api/Account/Upload");
        try {
            fileSystemStorage.saveFile(file);
            return "200";
        } catch (IOException e) {
            System.out.println(e.toString());
            return "201";
        }
	}

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/AssignFile/Avatar", method = RequestMethod.POST)
    public String assignFileAsAvatar(@RequestBody Map<String, String> registerInfo) {
        System.out.println("Start to assigning file");
        if (accountManager.checkUserName(registerInfo.get("username"))) {
            System.out.println("username has not been created!");
            return "201";
        }
        
        String username = registerInfo.get("username");
        String filename = registerInfo.get("filename");
        String originalPath = filename;
        String newPath = "users/" + username + "/" + "avatar.jpg";
        if (!fileSystemStorage.moveFile(originalPath, newPath)) {
            System.out.println("move file failed");
            return "201";
        }

        return "200";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/Download/Avatar", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadAvatar(String username) {
        System.out.println("handling /api/Account/Download/Avatar");
        if (accountManager.checkUserName(username)) {
            System.out.println("username has not been created!");
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }

        String filePath = "users/" + username + "/" + "avatar.jpg";

        File f;
        System.out.println("your avatar is from: " + filePath);

        try {
            f = fileSystemStorage.loadFile(filePath);
            InputStreamResource resource = new InputStreamResource(new FileInputStream(f));
            return ResponseEntity.ok()
                    .contentLength(f.length())
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } catch (FileNotFoundException e) {
            System.out.println("could not find file!");
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
    }
}
