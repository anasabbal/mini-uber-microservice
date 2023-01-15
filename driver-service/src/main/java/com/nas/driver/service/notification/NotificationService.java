package com.nas.driver.service.notification;


import com.nas.driver.model.NotificationDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Page<NotificationDriver> getNotificationsByDriverId(Pageable pageable, String driverId);
}
