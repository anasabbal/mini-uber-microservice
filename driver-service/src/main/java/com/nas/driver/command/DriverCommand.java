package com.nas.driver.command;


import com.nas.driver.enums.DriverStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DriverCommand {

    private String firstName;
    private String lastName;
    public DriverCommand(String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void validate(){

    }
}
