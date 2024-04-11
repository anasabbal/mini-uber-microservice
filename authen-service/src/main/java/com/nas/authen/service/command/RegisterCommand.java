package com.nas.authen.service.command;



import lombok.Getter;

@Getter
public class RegisterCommand {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String role;
}
