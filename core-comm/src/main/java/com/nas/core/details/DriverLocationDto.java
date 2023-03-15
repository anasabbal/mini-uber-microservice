package com.nas.core.details;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DriverLocationDto {
    protected String id;
    protected Boolean deleted;
    protected Boolean active;
    private String driverId;
    private String name;
    private Boolean available;
    private String carId;
    private Set<LocationEntityDto> locationEntities;
}
