package com.nas.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private String id;
    private String firstName;
    private String lastName;
    private String createdAt;
    private String updatedAt;
    private String updatedBy;
    private DriverStatusDto driverStatus;
    private List<NotificationDriverDto> notificationDrivers;
}
