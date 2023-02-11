package com.nas.location.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCommand {
    private String country;
    private String city;
    private String zip;
}
