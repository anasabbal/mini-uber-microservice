package com.nas.notification.service;


import com.nas.core.util.JSONUtil;
import com.nas.notification.command.NotificationRequest;
import com.nas.notification.dto.NotificationDto;
import com.nas.notification.dto.mapper.NotificationMapper;
import com.nas.notification.model.CustomerRequest;
import com.nas.notification.model.Notification;
import com.nas.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public Page<Notification> getAllNotifications(Pageable pageable) {
        final Page<NotificationDto> notifications = notificationRepository.
                findAll(pageable).
                map(notificationMapper::toDto);
        return notificationRepository.findAll(pageable);
    }
    public Notification create(final NotificationRequest notificationRequest){
        final Notification notification = Notification.create(notificationRequest);
        return notificationRepository.save(notification);
    }
    @KafkaListener(id = "notification_id", topics = "topic1")
    public void listenWhiteHeader(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", "notification");
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
        final Notification notification = create(new NotificationRequest(payload.value(), "this"));
    }
}
