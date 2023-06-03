package com.nas.driver.command;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CustomerRequestDriver implements Serializable {

    private String customerId;
    private String driverId;

    public static CustomerRequestDriver create(final String customerId, final String driverId){
        return new CustomerRequestDriver(customerId, driverId);
    }
}
