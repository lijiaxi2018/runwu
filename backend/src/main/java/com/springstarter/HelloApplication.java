package com.springstarter;


import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.springstarter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HelloApplication {

    public static void main(String[] args) {
        boolean server = true;
        if (server) {
            SpringApplication.run(HelloApplication.class, args);
        } else {
            AccountManager accountManager = new AccountManager();
            System.out.println(accountManager.loginUser("lin", "lin289034"));
        }
    }
}
