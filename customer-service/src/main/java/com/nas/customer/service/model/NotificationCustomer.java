package com.nas.customer.service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
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
    public void linkToCustomer(Customer customer){
        this.customer = customer;
    }
}
