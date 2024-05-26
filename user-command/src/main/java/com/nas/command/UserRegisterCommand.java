package com.nas.command;


import lombok.Getter;

import static com.nas.core.util.Assert.assertRegex;
import static com.nas.core.util.RegexExpressions.ALPHABETIC_MIN_2_CHARS;
import static com.nas.core.util.RegexExpressions.EMAIL;

@Getter
public class UserRegisterCommand {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void validate(){
        assertRegex(firstName, ALPHABETIC_MIN_2_CHARS);
        assertRegex(lastName, ALPHABETIC_MIN_2_CHARS);
        assertRegex(email, EMAIL);
    }
}
