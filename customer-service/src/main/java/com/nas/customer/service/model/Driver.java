package com.nas.customer.service.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver {
    protected String id;
    private String createdBy ="NAS SYSTEM";
    private String updatedBy;
    protected Boolean deleted = false;
    private String carId;
    private String firstName;
    private String lastName;
}
