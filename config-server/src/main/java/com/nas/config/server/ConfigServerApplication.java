package com.nas.config.server;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication{
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }


    @Value("${server.url:Unable to connect to config server}")
    private String uri;


    @GetMapping("/server/url")
    public String get(){
        return this.uri;
    }
}
