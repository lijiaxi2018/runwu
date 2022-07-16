package com.springstarter;

import com.mongodb.client.MongoClients;
import com.springstarter.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 *
 */
@Component
public class AccountManager {


    private final MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://admin:lin2890343180@localhost:27017"), "userdata");

    private int largestId;

    public AccountManager() {
        // get the largestId, this helps generate new user id
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("author_id"))).limit(1);
        User largestUser = mongoTemplate.findOne(query, User.class, "author");

        if (largestUser != null) {
            largestId = largestUser.getAuthor_id();
        } else {
            largestId = 0;
        }
    }
    /**
     *
     * @param username the username, the function will assume it is unique
     * @param password the password, the function will assume it is valid and secure
     * @param organization the organization of user
     * @return a pojo object to insert into the mongodb
     */
    public User createUser(String username, String password, String organization, String avatarFilename) {
        User newUser = new User(++largestId, username, password, avatarFilename, organization, new Date(), 0,0,0,0);
        mongoTemplate.insert(newUser, "author");
        return newUser;
    }

    /**
     * @param username the username the user want to create
     * @return true if the username is not in the database, false otherwise
     */
    public boolean checkUserName(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        if (mongoTemplate.findOne(query, User.class, "author") != null) {
            // the username has been used
            return false;
        }
        return true;
    }

    /**
     * @param password the username the new user want to use
     * @return true if password obey the password rules, false otherwise
     */
    public boolean checkPassword(String password) {
        //TODO: check the password
        return true;
    }

    /**
     *
     * @param username the login username
     * @param password the login password
     * @return object of user if username and password is correct, null otherwise
     */
    public User loginUser(String username, String password) {
        Query query = new Query(Criteria.where("username").is(username).and("password").is(password));
        return mongoTemplate.findOne(query, User.class, "author");
    }
}
