package com.nas.auth.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
