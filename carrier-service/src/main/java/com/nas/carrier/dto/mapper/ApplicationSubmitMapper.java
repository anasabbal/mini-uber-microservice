package com.nas.carrier.dto.mapper;


import com.nas.carrier.dto.ApplicationSubmitDto;
import com.nas.carrier.model.ApplicationSubmit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ApplicationSubmitMapper {
    public abstract ApplicationSubmitDto toDto(ApplicationSubmit applicationSubmit);
}
