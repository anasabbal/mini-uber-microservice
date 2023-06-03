package com.customer.service.dto.customer;

import com.customer.service.dto.notification.NotificationCustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<NotificationCustomerDto> notificationCustomers;
}

