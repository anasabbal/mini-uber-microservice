package com.nas.driver.model;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.NotificationDriverRequest;
import com.nas.driver.enums.DriverStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document(collection = "DRIVERS")
@Data
public class Driver{

    @Id
    protected String id;
    private LocalDateTime createdAt;
    private String createdBy ="NAS SYSTEM";
    private LocalDateTime updatedAt;
    private String updatedBy;
    protected Boolean deleted = false;
    private String carId;
    private String firstName;
    private String lastName;
    private DriverStatus driverStatus;
    @DBRef(db = "NOTIFICATIONS_DRIVER", lazy = true)
    private Set<NotificationDriver> notificationDriversId = new HashSet<>();

    public static Driver create(final DriverCommand driverCommand){
        final Driver driver = new Driver();

        driver.firstName = driverCommand.getFirstName();
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
    public void addToSet(NotificationDriver notificationDriver){
        this.notificationDriversId.add(notificationDriver);
    }
    public static Set<NotificationDriver> createNotificationPayload(Set<NotificationDriverRequest> notificationDriverRequests){
        return notificationDriverRequests
                .stream().map(
                        NotificationDriver::create)
                .collect(Collectors.toSet());
    }
}
