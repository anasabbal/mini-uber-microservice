package com.nas.driver.model;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.enums.DriverStatus;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "DRIVERS")
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Driver{

    @Id
    protected String id;

    private Integer version;

    private LocalDateTime createdAt;
    private String createdBy ="NAS SYSTEM";
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted = false;

    protected Boolean active = true;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;

    public static Driver create(final DriverCommand driverCommand){
        final Driver driver = new Driver();

        driver.firstName = driverCommand.getFirstName();;
        driver.lastName = driverCommand.getLastName();
        driver.createdBy = "NAS SYSTEM";
        driver.updatedBy = driver.firstName;
        driver.createdAt = LocalDateTime.now();
        driver.updatedAt = LocalDateTime.now();
        driver.driverStatus = DriverStatus.USER;
        return driver;
    }
}
