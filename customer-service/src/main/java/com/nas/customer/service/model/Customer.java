package com.nas.customer.service.model;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "DRIVER_ID")
    private String driverId = null;


    public static Customer create(final CustomerCommand command){
        final Customer customer = new Customer();

        customer.firstName = command.getFirstName();
        customer.lastName = command.getLastName();
        customer.email = command.getEmail();
        customer.password = command.getPassword();

        return customer;
    }
    public void updateInfo(final CustomerInfoUpdateCmd command){
        this.firstName = command.getFirstName();
        this.lastName = command.getLastName();
        this.email = command.getEmail();
    }

}
