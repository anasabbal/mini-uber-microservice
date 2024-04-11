package com.nas.authen.service.dto.mapper;


import com.nas.authen.service.dto.UserDto;
import com.nas.authen.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDto toDto(User user);
}
