package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDriver extends BaseEntity{


    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "DRIVER_ID")
    private String driverId;

    public static NotificationDriver create(final CustomerRequestDriver customerRequestDriver){
        final NotificationDriver notificationDriver = new NotificationDriver();

        notificationDriver.customerId = customerRequestDriver.getCustomerId();
        notificationDriver.driverId = customerRequestDriver.getDriverId();
        return notificationDriver;
    }
}
