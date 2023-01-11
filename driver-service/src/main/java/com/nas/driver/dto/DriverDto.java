package com.nas.driver.dto;


import com.nas.driver.enums.DriverStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DriverDto {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;
}
