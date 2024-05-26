package com.nas.authen.service.dto.mapper;

import com.nas.authen.service.dto.UserDto;
import com.nas.authen.service.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-11T15:53:35+0100",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240417-1011, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setActive( user.isActive() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setCreatedBy( user.getCreatedBy() );
        userDto.setDeleted( user.getDeleted() );
        userDto.setEmail( user.getEmail() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setId( user.getId() );
        userDto.setLastName( user.getLastName() );
        userDto.setPassword( user.getPassword() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setUpdatedBy( user.getUpdatedBy() );

        return userDto;
    }
}
