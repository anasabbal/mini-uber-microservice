package com.nas.driver.command;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.nas.core.util.Assert.assertRegex;
import static com.nas.core.util.RegexExpressions.ALPHABETIC_MIN_2_CHARS;

@Getter
@Setter
@NoArgsConstructor
public class DriverCommand {

    private String firstName;
    private String lastName;

    public void validate(){
        assertRegex(firstName, ALPHABETIC_MIN_2_CHARS);
        assertRegex(lastName, ALPHABETIC_MIN_2_CHARS);
    }
}
