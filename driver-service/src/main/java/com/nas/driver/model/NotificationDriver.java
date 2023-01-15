package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDriver extends BaseEntity{

    private String customerId;
    private String driverId;

    @ManyToOne
    private Driver driver;

    public static NotificationDriver create(final CustomerRequestDriver customerRequestDriver){
        final NotificationDriver notificationDriver = new NotificationDriver();

        notificationDriver.customerId = customerRequestDriver.getCustomerId();
        return notificationDriver;
    }
    public void linkToDriver(Driver driver){
        this.driver = driver;
    }
}
