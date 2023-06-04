package com.nas.driver.mapper;


import com.driver.service.dto.DriverStatusDto;
import com.nas.driver.model.DriverStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public  abstract class DriverStatusMapper {
    public abstract DriverStatusDto toDto(DriverStatus driverStatus);
}
