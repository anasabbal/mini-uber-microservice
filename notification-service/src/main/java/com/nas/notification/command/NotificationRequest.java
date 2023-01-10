package com.nas.notification.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    private String customerId;
    private String driverId;

    public NotificationRequest(String driverId, String customerId){
        this.customerId = customerId;
        this.driverId = driverId;
    }
}
