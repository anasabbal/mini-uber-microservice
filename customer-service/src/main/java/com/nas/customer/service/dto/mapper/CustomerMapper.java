package com.nas.customer.service.dto.mapper;


import com.nas.customer.service.dto.CustomerDto;
import com.nas.customer.service.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    public abstract CustomerDto toDto(Customer customer);
}