package com.nas.driver.model;


import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseEntity{

    @Column(name = "CARD_ID")
    private String carId;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private DriverStatus driverStatus;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "driver")
    private Set<NotificationDriver> notificationDrivers;
    @Column(name = "BANK_ACCOUNT_ID")
    private String bankAccountId;

    public static Driver create(final DriverCommand driverCommand){
        final Driver driver = new Driver();

        driver.firstName = driverCommand.getFirstName();
        driver.lastName = driverCommand.getLastName();
        driver.driverStatus = DriverStatus.create();

        return driver;
    }
    public void addToDriver(NotificationDriver notificationDriver){
        this.notificationDrivers.add(notificationDriver);
    }
    public void updateInfo(final DriverCommand driverCommand){
        this.firstName = driverCommand.getFirstName();
        this.lastName = driverCommand.getLastName();
    }
    public static Set<NotificationDriver> createNotificationPayload(Set<CustomerRequestDriver> customerRequestDrivers){
        return customerRequestDrivers
                .stream().map(
                        NotificationDriver::create)
                .collect(Collectors.toSet());
    }
}
