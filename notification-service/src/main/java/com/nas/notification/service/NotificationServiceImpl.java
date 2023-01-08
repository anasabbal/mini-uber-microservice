package com.nas.notification.service;


import com.nas.notification.dto.NotificationDto;
import com.nas.notification.dto.mapper.NotificationMapper;
import com.nas.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public Page<NotificationDto> getAllNotifications(Pageable pageable) {
        final Page<NotificationDto> notificationDtos = notificationRepository.
                findAll(pageable).
                map(notificationMapper::toDto);
        return null;
    }
    @KafkaListener(topics = "${topic.name.consumer}" , groupId = "group_id")
    public void listenWhiteHeader(ConsumerRecord<String, String> payload){
        log.info("Topic: {}", "notification");
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
    }
}
