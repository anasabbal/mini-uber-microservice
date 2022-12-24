package com.nas.customer.service.model;


import com.nas.customer.service.command.CustomerCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{

    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    public static Customer create(final CustomerCommand command){
        final Customer customer = new Customer();

        customer.firstName = command.getFirstName();
        customer.lastName = command.getLastName();
        customer.email = command.getEmail();
        customer.password = command.getPassword();

        return customer;
    }

}
