package com.nas.carrier.dto.mapper;


import com.nas.carrier.dto.CarrierDto;
import com.nas.carrier.model.Carrier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CarrierMapper {
    public abstract CarrierDto toDto(Carrier carrier);
}
