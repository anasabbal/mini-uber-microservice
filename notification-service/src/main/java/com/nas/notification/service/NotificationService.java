package com.nas.notification.service;


import com.nas.notification.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NotificationService {
    Page<Notification> getAllNotifications(Pageable pageable);
}
