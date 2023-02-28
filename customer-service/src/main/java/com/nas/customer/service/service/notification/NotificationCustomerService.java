package com.nas.customer.service.service.notification;

import com.nas.customer.service.model.NotificationCustomer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationCustomerService {
    Page<NotificationCustomer> getNotificationsCustomerById(String customerId, Pageable pageable);
}
