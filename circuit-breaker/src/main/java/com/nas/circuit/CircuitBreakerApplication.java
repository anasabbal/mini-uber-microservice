package com.nas.circuit;


import com.nas.circuit.config.WebClientConfiguration;
import io.github.resilience4j.circuitbreaker.configure.CircuitBreakerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({WebClientConfiguration.class, CircuitBreakerConfiguration.class})
public class CircuitBreakerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class, args);
    }
}
