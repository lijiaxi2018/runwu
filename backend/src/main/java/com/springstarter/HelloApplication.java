package com.springstarter;


import com.springstarter.service.AccountManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
