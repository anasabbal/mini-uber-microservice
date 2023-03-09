package com.nas.carrier.dto.mapper;


import com.nas.carrier.dto.JobDto;
import com.nas.carrier.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class JobMapper {
    public abstract JobDto toDto(Job job);
}
