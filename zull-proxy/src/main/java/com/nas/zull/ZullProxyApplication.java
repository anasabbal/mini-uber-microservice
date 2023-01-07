package com.nas.zull;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZullProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZullProxyApplication.class, args);
    }
}
