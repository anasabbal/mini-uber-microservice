package com.nas.circuit.example.service;


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final RestTemplate restTemplate;

    private final CircuitBreakerFactory circuitBreakerFactory;
    public String getAlbumList() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        String url = "https://jsonplaceholder.typicode.com/albums";

        return circuitBreaker.run(() -> restTemplate.getForObject(url, String.class));
    }
}
