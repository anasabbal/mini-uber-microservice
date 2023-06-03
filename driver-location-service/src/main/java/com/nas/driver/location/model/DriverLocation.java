package com.nas.driver.location.model;


import com.nas.driver.location.command.DriverLocationCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class DriverLocation extends BaseEntity{

    private String driverId;
    private String name;
    private Boolean available = true;
    private String carId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driverLocation")
    private Set<LocationEntity> locationEntities = new HashSet<>();

    public static DriverLocation create(final DriverLocationCommand driverLocationCommand){
        final DriverLocation driverLocation = new DriverLocation();

        driverLocation.name = driverLocationCommand.getName();
        return driverLocation;
    }
    public void addToSet(LocationEntity location){
        this.locationEntities.add(location);
        location.linkToDriver(this);
    }
}
