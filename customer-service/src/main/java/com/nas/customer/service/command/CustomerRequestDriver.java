package com.nas.customer.service.command;



import com.nas.core.location.Ndestination;
import lombok.*;
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
