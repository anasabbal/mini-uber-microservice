package com.nas.driver.location.model;


import com.nas.driver.location.command.DriverLocationCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
public class DriverLocation {
    @Id
    private String id;
    private String driverId;
    private String name;
    private Boolean available = true;
    private String carId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany
    private Set<LocationEntity> locationEntities = new HashSet<>();

    public static DriverLocation create(final DriverLocationCommand driverLocationCommand){
        final DriverLocation driverLocation = new DriverLocation();

        driverLocation.name = driverLocationCommand.getName();
        driverLocation.createdAt = LocalDateTime.now();
        driverLocation.updatedAt = LocalDateTime.now();
        return driverLocation;
    }
}
