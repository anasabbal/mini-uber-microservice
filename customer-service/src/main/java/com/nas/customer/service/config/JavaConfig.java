package com.nas.customer.service.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Configuration
public class JavaConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of("NAS_SYSTEM");
    }
}
