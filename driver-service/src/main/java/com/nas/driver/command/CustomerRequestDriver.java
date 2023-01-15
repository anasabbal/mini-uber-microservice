package com.nas.driver.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDriver {
    private String customerId;
    private String driverId;

    public static CustomerRequestDriver create(final String customerId, final String driverId){
        final CustomerRequestDriver customerRequestDriver = new CustomerRequestDriver();

        customerRequestDriver.customerId = customerId;
        customerRequestDriver.driverId = driverId;

        return customerRequestDriver;
    }
}
