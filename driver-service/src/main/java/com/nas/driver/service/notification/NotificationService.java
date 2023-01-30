package com.nas.driver.service.notification;


import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;


import java.util.List;

public interface NotificationService {

    List<NotificationDriver> getNotificationsByDriverId(String driverId);
    Driver acceptRequest(final String driverId, final AcceptRequestCustomer acceptRequestCustomer);
}
