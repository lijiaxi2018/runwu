package com.springstarter.controller;

import com.springstarter.AccountManager;
import com.springstarter.pojo.User;

import io.swagger.v3.oas.models.Paths;
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
import org.springframework.http.client.MultipartBodyBuilder;

import java.io.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import java.nio.file.Path;
import java.util.*;


@RestController
public class LoginController {

    private AccountManager accountManager = new AccountManager();

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
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException  {
        System.out.println("An image has been uploaded.");

        String filename = file.getOriginalFilename();
        String filepath = System.getProperty("user.dir") + "/src/main/resources/uploads/" + filename;
        System.out.println("Saved to " + filepath);

        File dest = new File(filepath);
        file.transferTo(dest);
		return "200";
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
        String originalPath = System.getProperty("user.dir") + "/src/main/resources/uploads/" + filename;
        String newPath = System.getProperty("user.dir") + "/src/main/resources/uploads/users/" + username + "/" + "avatar.jpg";

        File f = new File(originalPath);
        if(!f.exists()) { 
            System.out.println("file not existing!");
            return "201";
        }

        f.renameTo(new File(newPath));
        return "200";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/api/Account/Download/Avatar", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadAvatar(String username) throws FileNotFoundException, IOException {
        System.out.println("Start downloading file from server");
        if (accountManager.checkUserName(username)) {
            System.out.println("username has not been created!");
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
        
        String filePath = System.getProperty("user.dir") + "/src/main/resources/uploads/users/" + username + "/" + "avatar.jpg";

        File f = new File(filePath);
        if(!f.exists()) { 
            System.out.println("file not existing!");
            return ResponseEntity
                    .badRequest()
                    .body(null);
        }
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(f));

        return ResponseEntity.ok()
            .contentLength(f.length())
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(resource);
    }
}
