package com.nas.authen.service.model;


import com.nas.authen.service.command.RegisterCommand;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@Document(value = "USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    protected String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean active;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @LastModifiedBy
    private String updatedBy;
    private Boolean deleted = false;
    private Role role;


    public static User create(final RegisterCommand registerCommand){
        final User user = new User();

        user.firstName = registerCommand.getFirstName();
        user.lastName = registerCommand.getLastName();
        user.phoneNumber = registerCommand.getPhoneNumber();
        user.email = registerCommand.getEmail();
        user.password = registerCommand.getPassword();
        user.role = Role.createRole(registerCommand.getRole());
        return user;
    }
}
