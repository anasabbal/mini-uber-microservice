package com.nas.customer.service.service.notification;


import com.nas.customer.service.model.NotificationCustomer;
import com.nas.customer.service.repository.NotificationCustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationCustomerServiceImpl implements NotificationCustomerService{

    private final NotificationCustomerRepository notificationCustomerRepository;



    @Override
    public Page<NotificationCustomer> getNotificationsCustomerById(String customerId, Pageable pageable){
        log.info("[+] Begin fetching notification customer with id {}", customerId);
        return notificationCustomerRepository.findNotificationCustomersByCustomer_Id(customerId, pageable);
    }
}
