package com.nas.notification.dto;


import lombok.Data;

import javax.persistence.Column;

@Data
public class NotificationDto {
    private String driverId;

    private String customerId;

    private Boolean isNotification;
}
