package com.nas.notification.service;


import com.nas.notification.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NotificationService {
    Page<NotificationDto> getAllNotifications(Pageable pageable);
}
