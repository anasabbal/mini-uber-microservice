package com.nas.driver.dto.mapper;


import com.nas.driver.dto.DriverDto;
import com.nas.driver.model.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface  DriverMapper {

    @Mapping(source = "driverStatus.status", target = "status")
    DriverDto toDto(Driver driver);
}