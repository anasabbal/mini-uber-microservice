package com.nas.authen.service.dto.mapper;

import com.nas.authen.service.dto.UserDto;
import com.nas.authen.service.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T18:51:21+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setFirstName( user.getFirstName() );
        userDto.setLastName( user.getLastName() );
        userDto.setPhoneNumber( user.getPhoneNumber() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );
        userDto.setActive( user.isActive() );
        userDto.setCreatedAt( user.getCreatedAt() );
        userDto.setCreatedBy( user.getCreatedBy() );
        userDto.setUpdatedAt( user.getUpdatedAt() );
        userDto.setUpdatedBy( user.getUpdatedBy() );
        userDto.setDeleted( user.getDeleted() );

        return userDto;
    }
}
