package com.nas.driver.location.config;


import com.maxmind.geoip2.DatabaseReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class DriverConfig {
    @Bean
    public DatabaseReader databaseReader() throws Exception {
        File database = new File("/Users/anas/IdeaProjects/sport-micro/driver-location-service/src/main/resources/GeoLite2-City_20221230/GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(database)
                .build();
    }
}