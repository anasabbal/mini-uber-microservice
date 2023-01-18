package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.enums.DriverStatus;
import lombok.*;

import javax.persistence.CascadeType;
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

    private String carId;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "driver")
    private Set<NotificationDriver> notificationDrivers;

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
    }
    /*public void addToSet(NotificationDriver notificationDriver){
        this.notificationDrivers.add(notificationDriver);
    }*/
    public static Set<NotificationDriver> createNotificationPayload(Set<CustomerRequestDriver> customerRequestDrivers){
        return customerRequestDrivers
                .stream().map(
                        NotificationDriver::create)
                .collect(Collectors.toSet());
    }
}
