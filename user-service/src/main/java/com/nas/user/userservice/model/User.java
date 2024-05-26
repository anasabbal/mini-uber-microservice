package com.nas.user.userservice.model;


import com.nas.command.UserRegisterCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity{

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
