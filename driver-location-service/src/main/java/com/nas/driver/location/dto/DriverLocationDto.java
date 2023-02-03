package com.nas.driver.location.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class DriverLocationDto {
    protected String id;
    private Integer version;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted;
    protected Boolean active;
    private String driverId;
    private String name;
    private Boolean available;
    private String carId;
    private Set<LocationEntityDto> locationEntities;
}
