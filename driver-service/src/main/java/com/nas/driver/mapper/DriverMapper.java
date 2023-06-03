package com.nas.driver.mapper;


import com.driver.service.dto.DriverDto;
import com.nas.driver.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  DriverMapper {
    DriverDto toDto(Driver driver);
}