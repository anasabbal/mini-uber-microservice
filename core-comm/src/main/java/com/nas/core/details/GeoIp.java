package com.nas.core.details;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoIp {
    protected String id;
    private String ipAddress;
    private String country;
    private String city;
    private String latitude;
    private String longitude;
}