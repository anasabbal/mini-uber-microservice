package com.nas.driver.location.dto.mapper;


import com.nas.driver.location.dto.LocationEntityDto;
import com.nas.driver.location.model.LocationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationEntityMapper {
    LocationEntityDto toDto(LocationEntity location);
}
