package com.nas.driver.service.notification;

import com.nas.driver.command.NotificationDriverRequest;
import com.nas.driver.model.NotificationDriver;

public interface NotificationDriverService {


    NotificationDriver create(final NotificationDriverRequest notificationDriverRequest);
}
