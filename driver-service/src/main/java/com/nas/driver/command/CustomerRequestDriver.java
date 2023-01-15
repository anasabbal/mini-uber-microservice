package com.nas.driver.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDriver {
    private String customerId;

    public static CustomerRequestDriver create(final String customerId){
        final CustomerRequestDriver customerRequestDriver = new CustomerRequestDriver();

        customerRequestDriver.customerId = customerId;

        return customerRequestDriver;
    }
}
