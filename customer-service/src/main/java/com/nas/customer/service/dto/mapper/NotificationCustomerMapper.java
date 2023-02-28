package com.nas.customer.service.dto.mapper;


import com.nas.customer.service.dto.NotificationCustomerDto;
import com.nas.customer.service.model.NotificationCustomer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NotificationCustomerMapper {

    public abstract NotificationCustomerDto toDto(NotificationCustomer notificationCustomer);
}
