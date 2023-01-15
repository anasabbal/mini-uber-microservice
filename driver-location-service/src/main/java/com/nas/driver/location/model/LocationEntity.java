package com.nas.driver.location.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LocationEntity extends BaseEntity{


    @ManyToOne
    private DriverLocation driverLocation;
    private String geoIp;
}
