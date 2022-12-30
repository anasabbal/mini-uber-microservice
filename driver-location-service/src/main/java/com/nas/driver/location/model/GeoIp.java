package com.nas.driver.location.model;


import lombok.Data;


@Data
public class GeoIp {
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
