package com.nas.driver.dto;


import com.nas.driver.model.DriverStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private DriverStatus driverStatus;
    private List<NotificationDriverDto> notificationDrivers;
}
