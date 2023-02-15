package com.nas.driver.dto.mapper;


import com.nas.driver.dto.NotificationDriverDto;
import com.nas.driver.model.NotificationDriver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NotificationDriverMapper {
    public abstract NotificationDriverDto toDto(NotificationDriver notificationDriver);
}