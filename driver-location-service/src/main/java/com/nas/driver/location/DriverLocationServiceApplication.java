package com.nas.driver.location;


import com.nas.driver.location.repository.DriverLocationRepository;
import com.nas.driver.location.repository.LocationEntityRepository;
import com.nas.driver.location.service.DriverLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(value = "com.nas.driver.location.repository")
public class DriverLocationServiceApplication implements CommandLineRunner {


    @Autowired
    private DriverLocationRepository driverLocationRepository;

    @Autowired
    private DriverLocationService driverLocationService;

    @Autowired
    private LocationEntityRepository locationEntityRepository;


    public static void main(String[] args) {
        SpringApplication.run(DriverLocationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
