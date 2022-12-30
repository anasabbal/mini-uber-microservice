package com.nas.customer.service.service;

import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Customer create(final CustomerCommand customerCommand);
    Page<Customer> findAllByDeletedFalse(Pageable pageable);
    Customer findById(String customerId);
    Customer get(final String driverId);
}