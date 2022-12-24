package com.nas.customer.service.command;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CustomerCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
