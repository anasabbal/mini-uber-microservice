package com.nas.customer.service.model;


import jakarta.persistence.ManyToOne;
import lombok.*;

import jakarta.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationCustomer extends BaseEntity{
    private String driverId;

    @ManyToOne
    private Customer customer;

    public static NotificationCustomer create(final String driverId){
        final NotificationCustomer notificationCustomer = new NotificationCustomer();

        notificationCustomer.driverId = driverId;

        return notificationCustomer;
    }
}
