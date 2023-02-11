package com.nas.customer.service.command;



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

}
