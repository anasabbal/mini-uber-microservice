package com.nas.driver.service.notification;


import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    List<NotificationDriver> getNotificationsByDriverId(String driverId);
    Driver acceptRequest(final AcceptRequestCustomer acceptRequestCustomer);
    Page<NotificationDriver> getAll(Pageable pageable);
    Page<NotificationDriver> findAllByDriverId(String driverId);
}
