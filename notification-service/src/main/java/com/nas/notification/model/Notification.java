package com.nas.notification.model;


import com.nas.notification.command.NotificationRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity{

    private Map<String, String> map = new HashMap<>();


    public static Notification create(final NotificationRequest notificationRequest){
        final Notification notification = new Notification();

        notification.map.put(notificationRequest.getCustomerId(), notificationRequest.getDriverId());
        return notification;
    }
}
