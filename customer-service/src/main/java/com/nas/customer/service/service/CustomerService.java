package com.nas.customer.service.service;

import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;

public interface CustomerService {
    Customer create(final CustomerCommand customerCommand);
}
