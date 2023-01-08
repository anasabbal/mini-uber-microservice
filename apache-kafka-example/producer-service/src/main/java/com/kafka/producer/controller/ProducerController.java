package com.kafka.producer.controller;


import com.kafka.producer.service.TopicProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProducerController {

    private final TopicProducer topicProducer;

    @GetMapping (value = "/send")
    public void send(){
        topicProducer.send("Vu!!");
    }
}