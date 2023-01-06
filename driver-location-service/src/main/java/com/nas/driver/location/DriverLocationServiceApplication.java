package com.nas.driver.location;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(value = "com.nas.driver.location.repository")
public class DriverLocationServiceApplication{


    public static void main(String[] args) {
        SpringApplication.run(DriverLocationServiceApplication.class, args);
    }

}
