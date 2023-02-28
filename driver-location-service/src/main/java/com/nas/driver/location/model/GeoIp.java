package com.nas.driver.location.model;



import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class GeoIp{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;

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
