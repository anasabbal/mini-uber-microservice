package com.nas.driver.controller;


import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.dto.DriverDto;
import com.nas.driver.dto.NotificationDriverDto;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.dto.mapper.NotificationDriverMapper;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.nas.core.constants.ResourcePath.*;

@RestController
@RequestMapping(V1 + NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationDriverController {

    private final NotificationService notificationService;
    private final NotificationDriverMapper notificationDriverMapper;
    private final DriverMapper driverMapper;


    @GetMapping(DRIVERS + "/{driverId}")
    public ResponseEntity<Page<NotificationDriverDto>> getNotificationsByDriverId(@PathVariable("driverId") final String driverId){
       final List<NotificationDriver> notificationDriverList = notificationService.getNotificationsByDriverId(driverId);
       final Page<NotificationDriver> notificationDrivers = new PageImpl<>(notificationDriverList);
        return ResponseEntity.ok(notificationDrivers.map(notificationDriverMapper::toDto));
    }
    @PostMapping(REQUEST )
    public ResponseEntity<DriverDto> acceptRequest(
                                                   @RequestBody final AcceptRequestCustomer acceptRequestCustomer){
        final Driver driver = notificationService.acceptRequest(acceptRequestCustomer);
        return ResponseEntity.ok(driverMapper.toDto(driver));
    }
}
