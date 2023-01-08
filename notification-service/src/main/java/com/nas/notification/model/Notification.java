package com.nas.notification.model;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Notification extends BaseEntity{

    @Column(name = "DRIVER_ID")
    private String driverId;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "IS_NOTIFICATION")
    private Boolean isNotification;
}
