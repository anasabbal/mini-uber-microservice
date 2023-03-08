package com.nas.driver.model;


import com.nas.driver.command.AddressCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity{

    private String street;
    private String city;
    private String country;

    @ManyToOne(cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
    private Driver driver;

    public static <S extends AddressCommand>  Address create(final S addressCommand){
        final Address address = new Address();
        address.street = addressCommand.getStreet();
        address.city = addressCommand.getCity();
        address.country = addressCommand.getCountry();

        return address;
    }
    public void update(final AddressCommand addressCommand){
        this.city = addressCommand.getCity();
        this.country = addressCommand.getCountry();
        this.street = addressCommand.getStreet();
    }
}
