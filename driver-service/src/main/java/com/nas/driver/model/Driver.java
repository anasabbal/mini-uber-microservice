package com.nas.driver.model;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.DriverUpdateStatus;
import com.nas.driver.enums.DriverStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "DRIVERS")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Driver{

    @Id
    protected String id;
    private LocalDateTime createdAt;
    private String createdBy ="NAS SYSTEM";
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted = false;
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
        driver.driverStatus = DriverStatus.AVAILABLE;
        return driver;
    }
    public void updateInfo(final DriverCommand driverCommand){
        this.firstName = driverCommand.getFirstName();
        this.lastName = driverCommand.getLastName();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = this.firstName;
    }
}
