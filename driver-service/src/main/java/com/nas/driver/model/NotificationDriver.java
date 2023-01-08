package com.nas.driver.model;


import com.nas.driver.command.NotificationDriverRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "NOTIFICATIONS_DRIVER")
public class NotificationDriver {

    @Id
    private String id;
    private String customerId;

    public static NotificationDriver create(final NotificationDriverRequest notificationDriverRequest){
        final NotificationDriver notificationDriver = new NotificationDriver();

        notificationDriver.customerId = notificationDriverRequest.getCustomerId();
        return notificationDriver;
    }
}
