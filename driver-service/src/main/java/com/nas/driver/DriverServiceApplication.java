package com.nas.driver;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.enums.DriverStatus;
import com.nas.driver.model.Driver;
import com.nas.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(value = "com.nas.driver.repository")
public class DriverServiceApplication implements CommandLineRunner {

    @Autowired
    private DriverRepository driverRepository;


    public static void main(String[] args) {
        SpringApplication.run(DriverServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Driver driver = new Driver();
        driver.setCreatedAt(LocalDateTime.now());
        driver.setActive(true);
        driver.setUpdatedAt(LocalDateTime.now());
        driver.setUpdatedBy("NAS");
        driver.setCreatedBy("NAS");
        driver.setDeleted(false);
        driver.setDriverStatus(DriverStatus.ADMIN);

        DriverCommand driverCommand = new DriverCommand("2PI", "2PI", LocalDateTime.now(), LocalDateTime.now());

        Driver driver1 = Driver.create(driverCommand);
        List<Driver> list = new ArrayList<>();
        list.add(driver);
        list.add(driver1);
        driverRepository.saveAll(list);
    }
}