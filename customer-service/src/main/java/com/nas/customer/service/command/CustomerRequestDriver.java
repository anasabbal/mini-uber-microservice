package com.nas.customer.service.command;


import com.nas.core.location.Ndestination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
public class  CustomerRequestDriver implements Serializable {
    private String customerId;
    private String driverId;
    private Ndestination destination;
}
