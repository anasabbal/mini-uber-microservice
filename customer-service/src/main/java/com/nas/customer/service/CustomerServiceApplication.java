package com.nas.customer.service;


import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.spring.messaging.checkpoint.Checkpointer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Sinks;

import static com.azure.spring.messaging.AzureHeaders.CHECKPOINTER;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceApplication{


    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public Consumer<String> onReceive() {
        return (message) -> {
            log.info("Received the value {} in Consumer", message);
        };
    }
    @Bean
    public Function<String, String> convertToUppercase() {
        return (value) -> {
            log.info("Received {}", value);
            String upperCaseValue = value.toUpperCase();
            log.info("Sending {}", upperCaseValue);
            return upperCaseValue;
        };
    }
}