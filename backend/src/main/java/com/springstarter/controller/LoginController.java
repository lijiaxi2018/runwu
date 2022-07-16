package com.springstarter.controller;


import com.springstarter.AccountManager;
import com.springstarter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    private AccountManager accountManager = new AccountManager();

    @RequestMapping("/")
    public String index() {
        return "server is started";
    }


    @RequestMapping(value = "/api/Account/SignIn", method = RequestMethod.GET)
    public String login(String username, String password) {
        User gotUser = accountManager.loginUser(username, password);
        if (gotUser != null) {
            return "200";
        } else {
            return "201";
        }
    }

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
        System.out.println("user created successfully!");
        return "200";
    }
}
