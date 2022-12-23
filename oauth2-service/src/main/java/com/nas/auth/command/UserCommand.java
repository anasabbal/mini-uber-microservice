package com.nas.auth.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCommand {
    private String userName;
    private String email;
    private String password;

    public void validate(){

    }
}
