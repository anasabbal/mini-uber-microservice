package com.nas.notification.dto.mapper;


import com.nas.notification.dto.NotificationDto;
import com.nas.notification.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class NotificationMapper {
    public abstract NotificationDto toDto(Notification notification);
}
