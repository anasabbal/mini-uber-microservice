package com.nas.driver.service.notification;

import com.nas.driver.command.NotificationDriverRequest;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface NotificationDriverService {


    NotificationDriver create(final NotificationDriverRequest notificationDriverRequest);
    void listenWhiteHeader(ConsumerRecord<String, String> payload);
    boolean updateNotificationDriver(Driver driver,
                                     NotificationDriver notificationDriver);
}
