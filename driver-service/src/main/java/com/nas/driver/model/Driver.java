package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.enums.DriverStatus;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver extends BaseEntity{

    private LocalDateTime updatedAt;
    private String updatedBy;
    private String carId;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "driver")
    private Set<NotificationDriver> notificationDriversId;

    public static Driver create(final DriverCommand driverCommand){
        final Driver driver = new Driver();

        driver.firstName = driverCommand.getFirstName();
        driver.lastName = driverCommand.getLastName();
        driver.driverStatus = DriverStatus.AVAILABLE;
        return driver;
    }
    public void updateInfo(final DriverCommand driverCommand){
        this.firstName = driverCommand.getFirstName();
        this.lastName = driverCommand.getLastName();
        this.updatedAt = LocalDateTime.now();
        this.updatedBy = this.firstName;
    }
    public void addToSet(NotificationDriver notificationDriver){
        notificationDriver.linkToDriver(this);
    }
    public static Set<NotificationDriver> createNotificationPayload(Set<CustomerRequestDriver> customerRequestDrivers){
        return customerRequestDrivers
                .stream().map(
                        NotificationDriver::create)
                .collect(Collectors.toSet());
    }
}
