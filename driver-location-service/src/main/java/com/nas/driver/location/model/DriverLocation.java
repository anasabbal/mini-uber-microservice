package com.nas.driver.location.model;


import com.nas.driver.location.command.DriverLocationCommand;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
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
    private Set<LocationEntity> locationEntities;

    public static DriverLocation create(final DriverLocationCommand driverLocationCommand){
        final DriverLocation driverLocation = new DriverLocation();

        driverLocation.name = driverLocationCommand.getName();
        return driverLocation;
    }
}
