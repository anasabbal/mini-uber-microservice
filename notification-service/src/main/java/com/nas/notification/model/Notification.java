package com.nas.notification.model;


import com.nas.notification.command.NotificationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity{

    @Column(name = "DRIVER_ID")
    private String driverId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "IS_NOTIFICATION")
    private Boolean isNotification;


    public static Notification create(final NotificationRequest notificationRequest){
        final Notification notification = new Notification();

        notification.driverId = notificationRequest.getDriverId();
        notification.customerId = notificationRequest.getCustomerId();

        return notification;
    }
}
