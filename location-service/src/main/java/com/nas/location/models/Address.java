package com.nas.location.models;

import com.nas.location.command.AddressCommand;

public class Address {
    private String country;
    private String city;
    private String zip;

    public static <T extends AddressCommand> Address create(final T addressCommand){
        final Address address = new Address();

        address.country = addressCommand.getCountry();
        address.city = addressCommand.getCity();
        address.zip = addressCommand.getZip();

        return address;
    }
}
