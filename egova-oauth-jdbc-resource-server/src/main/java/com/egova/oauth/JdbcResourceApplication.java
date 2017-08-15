package com.egova.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * to use share jdbc to store token and authenticate token
 */
@SpringBootApplication
@Configuration
public class JdbcResourceApplication {


    public static void main(String[] args) {
        SpringApplication.run(JdbcResourceApplication.class, args);
    }


}
