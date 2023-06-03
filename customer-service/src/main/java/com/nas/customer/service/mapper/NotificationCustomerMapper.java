package com.nas.customer.service.mapper;


import com.customer.service.dto.notification.NotificationCustomerDto;
import com.nas.customer.service.model.NotificationCustomer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NotificationCustomerMapper {

    public abstract NotificationCustomerDto toDto(NotificationCustomer notificationCustomer);
}
