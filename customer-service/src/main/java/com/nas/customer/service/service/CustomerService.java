package com.nas.customer.service.service;

import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.command.RatingCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface CustomerService {
    Customer create(final CustomerCommand customerCommand);
    Page<Customer> findAllByDeletedFalse(Pageable pageable);
    Customer findById(String customerId);
    void updateInfo(final CustomerInfoUpdateCmd customerCommand, String customerId);
    Set<Driver> getDriversAvailable();
    void sendRequestDriver(CustomerRequestDriver requestDriver);
    String sendRating(final RatingCommand ratingCommand);
}