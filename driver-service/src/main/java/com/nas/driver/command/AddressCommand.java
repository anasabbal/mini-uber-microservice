package com.nas.driver.command;

import com.nas.core.util.AssertValidation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressCommand  {
    private String street;
    private String city;
    private String country;

    public void validate() {
        AssertValidation.isEmpty(street);
        AssertValidation.isEmpty(city);
        AssertValidation.isEmpty(country);
    }
}