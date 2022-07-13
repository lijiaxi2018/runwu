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

    @Autowired
    private static MongoTemplate mongoTemplate;

    public static void main(String[] args) {
//        SpringApplication.run(HelloApplication.class, args);
        try {
//            mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://admin:lin2890343180@localhost:27017"), "userdata");
//            Query query = Query.query(Criteria.where("username").is("lin"));
//            List<User> one = mongoTemplate.findAll(User.class);
//            System.out.println(one);
            AccountManager a = new AccountManager();
//            a.createUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
