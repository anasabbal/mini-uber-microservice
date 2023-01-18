package com.nas.driver.controller;


import com.nas.driver.dto.NotificationDriverDto;
import com.nas.driver.dto.mapper.NotificationDriverMapper;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.constants.ResourcePath.*;

@RestController
@RequestMapping(V1 + NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationDriverController {

    private final NotificationService notificationService;
    private final NotificationDriverMapper notificationDriverMapper;


    @GetMapping(DRIVERS + "/{driverId}")
    public ResponseEntity<Page<NotificationDriverDto>> getNotificationsByDriverId(@PathVariable("driverID") final String driverId, Pageable pageable){
        final Page<NotificationDriver> notificationDrivers = notificationService.getNotificationsByDriverId(pageable, driverId);
        return ResponseEntity.ok(notificationDrivers.map(notificationDriverMapper::toDto));
    }
}
