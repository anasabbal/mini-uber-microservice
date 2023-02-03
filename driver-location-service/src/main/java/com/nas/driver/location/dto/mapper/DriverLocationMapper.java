package com.nas.driver.location.dto.mapper;


import com.nas.driver.location.dto.DriverLocationDto;
import com.nas.driver.location.model.DriverLocation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverLocationMapper {
    DriverLocationDto toDto(DriverLocation driverLocation);
}
