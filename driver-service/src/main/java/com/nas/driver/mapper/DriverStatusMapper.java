package com.nas.driver.mapper;


import com.nas.driver.dto.DriverStatusDto;
import com.nas.driver.model.DriverStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public  abstract class DriverStatusMapper {
    public abstract DriverStatusDto toDto(DriverStatus driverStatus);
}
