package com.nas.driver.dto.mapper;


import com.nas.driver.dto.DriverDto;
import com.nas.driver.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  DriverMapper {

    DriverDto toDto(Driver driver);
}