package com.nas.driver.mapper;


import com.nas.driver.dto.DriverDto;
import com.nas.driver.model.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DriverMapper {
    public abstract DriverDto toDto(Driver driver);
}
