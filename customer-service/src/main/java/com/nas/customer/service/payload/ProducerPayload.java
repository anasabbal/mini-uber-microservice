package com.nas.customer.service.payload;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class ProducerPayload <K, V>{

    private final KafkaTemplate<K, V> kafkaTemplate;
    private final String topic;


    public void send(K key, V value) {
        kafkaTemplate.send(topic, key, value);
    }
}
