package com.nas.customer.service.service;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.NotificationCustomer;
import com.nas.customer.service.repository.CustomerRepository;
import com.nas.customer.service.repository.NotificationCustomerRepository;
import com.nas.customer.service.service.notification.NotificationCustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {


    @Mock
    private NotificationCustomerRepository notificationCustomerRepository;


    @InjectMocks
    private NotificationCustomerServiceImpl notificationCustomerService;


    @Test
    public void can_i_get_notification_with_customer_id(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        final CustomerCommand customerCommand = new CustomerCommand();
        customerCommand.setFirstName("anas");
        customerCommand.setLastName("abbal");
        customerCommand.setPassword("zadina123");
        customerCommand.setEmail("anas.abbal10@gmail.com");

        final Customer customer = Customer.create(customerCommand);

        final List<NotificationCustomer> notificationCustomers = Arrays.asList(
                new NotificationCustomer(),
                new NotificationCustomer(),
                new NotificationCustomer()
        );
        customer.setNotificationCustomers(notificationCustomers);

        when(notificationCustomerRepository.findNotificationCustomersByCustomer_Id(
                customer.getId(), pageRequest)
        ).thenReturn(new PageImpl<>(notificationCustomers));

        // Test get
        notificationCustomerService.getNotificationsCustomerById(customer.getId(), pageRequest);
        assertThat(notificationCustomerService.getNotificationsCustomerById(customer.getId(), pageRequest).getSize()).isEqualTo(3);
    }
    @Test
    public void should_can_i_delete_all_notification_by_customer_id(){
    }
}
