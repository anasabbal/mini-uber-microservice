package com.nas.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class OAuth2AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuth2AuthenticationServiceApplication.class, args);
    }
}