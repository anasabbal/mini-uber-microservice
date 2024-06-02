package com.nas.user.userservice.model;


import com.nas.command.UserRegisterCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;



@Setter
@Getter
@Document
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;

    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "DELETED")
    protected Boolean deleted = false;

    @Column(name = "ACTIVE")
    protected Boolean active = true;

    public static User create(final UserRegisterCommand command) {
        final User user = new User();

        user.username = command.getUsername();
        user.firstName = command.getFirstName();
        user.lastName = command.getLastName();
        user.email = command.getEmail();
        user.password = command.getPassword();

        return user;
    }
}
