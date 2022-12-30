package com.nas.customer.service.command;


import lombok.Getter;
import lombok.Setter;

import static com.nas.core.RegexExpressions.ALPHABETIC_MIN_2_CHARS;
import static com.nas.core.RegexExpressions.EMAIL;
import static com.nas.customer.service.util.Assert.assertRegex;

@Getter
@Setter
public class CustomerInfoUpdateCmd {
    private String firstName;
    private String lastName;
    private String email;

    public void validate(){
        assertRegex(firstName, ALPHABETIC_MIN_2_CHARS);
        assertRegex(lastName, ALPHABETIC_MIN_2_CHARS);
        assertRegex(email, EMAIL);
    }
}
