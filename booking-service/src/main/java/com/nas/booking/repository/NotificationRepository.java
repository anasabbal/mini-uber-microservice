package com.nas.booking.repository;

import com.nas.booking.models.Notification;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends BaseRepository<String, Notification> {
}
