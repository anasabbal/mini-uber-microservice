package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDriver extends BaseEntity{
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
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
