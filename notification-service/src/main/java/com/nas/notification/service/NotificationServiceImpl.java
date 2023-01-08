package com.nas.notification.service;


import com.nas.core.util.JSONUtil;
import com.nas.notification.dto.NotificationDto;
import com.nas.notification.dto.mapper.NotificationMapper;
import com.nas.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name.consumer}")
    private String topicName;

    @Override
    public Page<NotificationDto> getAllNotifications(Pageable pageable) {
        final Page<NotificationDto> notificationDtos = notificationRepository.
                findAll(pageable).
                map(notificationMapper::toDto);

    }

    public void sendNotifcationId(Pageable pageable){
        final Page<NotificationDto> notificationDtos1 = getAllNotifications(pageable);
        log.info("Begin sending payload {}", JSONUtil.toJSON(notificationDtos1));
        kafkaTemplate.send(topicName, notificationDtos1);
    }
}
