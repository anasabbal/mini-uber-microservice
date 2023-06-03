package com.nas.customer.service.mapper;


import com.customer.service.dto.customer.CustomerDto;
import com.nas.customer.service.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CustomerMapper {
    public abstract CustomerDto toDto(Customer customer);
}