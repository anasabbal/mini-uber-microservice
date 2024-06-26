package com.nas.customer.service.service.customer;

import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.command.RatingCommand;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.payload.CustomerDetails;
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
    Page<Customer> getAllByCriteria(Pageable pageable, CustomerCriteria customerCriteria);
    CustomerDetails findCustomerDetailsById(String customerId);
}