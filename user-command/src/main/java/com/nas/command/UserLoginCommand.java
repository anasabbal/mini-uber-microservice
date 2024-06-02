package com.nas.command;


import lombok.Getter;

import static com.nas.core.util.Assert.assertRegex;
import static com.nas.core.util.RegexExpressions.EMAIL;

@Getter
public class UserLoginCommand {
    private String email;
    private String password;


    public void validate(){
        assertRegex(email, EMAIL);
    }
}
