package com.nas.driver.command;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDriverRequest {
    private String customerId;

    public static NotificationDriverRequest create(final String customerId){
        final NotificationDriverRequest notificationDriverRequest = new NotificationDriverRequest();
        notificationDriverRequest.customerId = customerId;

        return notificationDriverRequest;
    }
}
