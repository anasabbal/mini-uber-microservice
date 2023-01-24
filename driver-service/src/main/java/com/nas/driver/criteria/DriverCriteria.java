package com.nas.driver.criteria;


import com.nas.driver.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverCriteria {
    private String firstName;
    private String lastName;
    private DriverStatus status;
}
