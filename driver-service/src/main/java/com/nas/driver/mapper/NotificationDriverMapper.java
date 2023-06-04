package com.nas.driver.mapper;


import com.driver.service.dto.NotificationDriverDto;
import com.nas.driver.model.NotificationDriver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NotificationDriverMapper {
    public abstract NotificationDriverDto toDto(NotificationDriver notificationDriver);
}