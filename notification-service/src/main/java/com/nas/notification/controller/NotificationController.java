package com.nas.notification.controller;


import com.nas.notification.model.Notification;
import com.nas.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.constants.ResourcePath.V1;
import static com.nas.core.constants.ResourcePath.NOTIFICATIONS;

@RestController
@RequestMapping(V1 + NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;



    @GetMapping
    public ResponseEntity<Page<Notification>> getAll(Pageable pageable){
        return ResponseEntity.ok(notificationService.getAllNotifications(pageable));
    }
}
