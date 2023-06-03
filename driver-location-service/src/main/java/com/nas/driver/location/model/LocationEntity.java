package com.nas.driver.location.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LocationEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;

    @ManyToOne
    private DriverLocation driverLocation;

    @OneToOne
    private GeoIp geoIp;


    public void linkToDriver(DriverLocation driverLocation){
        this.driverLocation = driverLocation;
    }
}
