package com.nas.driver.dto;


import com.nas.driver.enums.DriverStatus;
import com.nas.driver.model.NotificationDriver;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

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
