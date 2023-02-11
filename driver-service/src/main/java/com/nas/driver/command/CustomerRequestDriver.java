package com.nas.driver.command;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class CustomerRequestDriver {

    private String customerId;
    private String driverId;

    public static CustomerRequestDriver create(final String customerId, final String driverId){
        return new CustomerRequestDriver(customerId, driverId);
    }
}
