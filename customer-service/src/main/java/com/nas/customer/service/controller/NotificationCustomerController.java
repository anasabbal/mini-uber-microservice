package com.nas.customer.service.controller;


import com.nas.customer.service.dto.NotificationCustomerDto;
import com.nas.customer.service.mapper.NotificationCustomerMapper;
import com.nas.customer.service.model.NotificationCustomer;
import com.nas.customer.service.service.notification.NotificationCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.nas.core.constants.ResourcePath.NOTIFICATION_CUSTOMER;
import static com.nas.core.constants.ResourcePath.V1;

@RestController
@RequestMapping(V1 + NOTIFICATION_CUSTOMER)
@RequiredArgsConstructor
public class NotificationCustomerController {

    private final NotificationCustomerService notificationCustomerService;
    private final NotificationCustomerMapper notificationCustomerMapper;


    @GetMapping("/{customerId}")
    public ResponseEntity<Page<NotificationCustomerDto>> getAll(@PathVariable("customerId") final String customerId, Pageable pageable){
        final Page<NotificationCustomer> notificationCustomers = notificationCustomerService.getNotificationsCustomerById(customerId, pageable);
        return ResponseEntity.ok(notificationCustomers.map(notificationCustomerMapper::toDto));
    }
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> emptyNotifications(@PathVariable("customerId") final String customerId){
        notificationCustomerService.deleteAllNotificationByCustomerId(customerId);
        return ResponseEntity.noContent().build();
    }
}
