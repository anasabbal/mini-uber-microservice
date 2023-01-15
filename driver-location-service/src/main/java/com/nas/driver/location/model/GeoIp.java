package com.nas.driver.location.model;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;


@Data
public class GeoIp {


    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    private String ipAddress;
    private String country;
    private String city;
    private String latitude;
    private String longitude;

    public GeoIp(String country, String ipAddress, String city, String latitude, String longitude) {
        this.country = country;
        this.ipAddress = ipAddress;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
