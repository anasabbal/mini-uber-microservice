package com.nas.driver.location;


import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.nas.driver.location.repository.DriverLocationRepository;
import com.nas.driver.location.service.DriverLocationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.wololo.geojson.Feature;

@SpringBootApplication
@EnableMongoRepositories(value = "com.nas.driver.location.repository")
public class DriverLocationServiceApplication implements CommandLineRunner {


    @Autowired
    private DriverLocationRepository driverLocationRepository;


    @Autowired
    private DriverLocationService driverLocationService;


    public static void main(String[] args) {
        SpringApplication.run(DriverLocationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LocationEntity location = new LocationEntity();
        Feature feature = new Feature();
    }
}
