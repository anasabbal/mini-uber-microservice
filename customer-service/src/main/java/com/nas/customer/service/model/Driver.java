package com.nas.customer.service.model;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Driver {
    protected String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private String firstName;
    private String lastName;
    private String driverStatus;
}
