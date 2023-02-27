package com.nas.driver.location.config;


import com.maxmind.geoip2.DatabaseReader;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Optional;

@Configuration
public class DriverConfig {
    @Bean
    public DatabaseReader databaseReader() throws Exception {
        File database = new File("/Users/anas/IdeaProjects/mini-uber-microservice/driver-location-service/src/main/resources/GeoLite2-City_20221230/GeoLite2-City.mmdb");
        return new DatabaseReader.Builder(database)
                .build();
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("NAS");
    }
}