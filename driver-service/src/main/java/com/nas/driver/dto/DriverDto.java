package com.nas.driver.dto;


import com.nas.driver.enums.DriverStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DriverDto {
    protected String id;
    private LocalDateTime createdAt;
    private String createdBy;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
