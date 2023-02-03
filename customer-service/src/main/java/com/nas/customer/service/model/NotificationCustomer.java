package com.nas.customer.service.model;


import lombok.*;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationCustomer extends BaseEntity{
    private String driverId;


    public static NotificationCustomer create(final String driverId){
        final NotificationCustomer notificationCustomer = new NotificationCustomer();

        notificationCustomer.driverId = driverId;

        return notificationCustomer;
    }
}
