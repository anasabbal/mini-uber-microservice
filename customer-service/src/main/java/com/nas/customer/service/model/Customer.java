package com.nas.customer.service.model;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Customer extends BaseEntity{

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "BANK_ACCOUNT_ID")
    private String bankAccountId;
    @Column(name = "DRIVER_ID")
    private String driverId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "customer")
    private List<NotificationCustomer> notificationCustomers;


    public static Customer create(final CustomerCommand command){
        final Customer customer = new Customer();

        customer.firstName = command.getFirstName();
        customer.lastName = command.getLastName();
        customer.email = command.getEmail();
        customer.password = command.getPassword();

        return customer;
    }
    public void linkNotification(String driverId){
        final NotificationCustomer notificationCustomer = NotificationCustomer.create(driverId);
        notificationCustomer.linkToCustomer(this);
        this.notificationCustomers.add(notificationCustomer);
    }
    public void updateInfo(final CustomerInfoUpdateCmd command){
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.email = command.getEmail();
    }
    public void emptyNotification(){
        this.notificationCustomers.clear();
    }
}
