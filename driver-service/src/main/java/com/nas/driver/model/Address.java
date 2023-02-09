package com.nas.driver.model;


import com.nas.driver.command.AddressCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity{
    private String street;
    private String city;
    private String country;
    @ManyToOne(optional = false)
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
