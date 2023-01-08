package com.nas.customer.service.service;


import com.nas.core.util.JSONUtil;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.model.DriverSet;
import com.nas.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${topic.name.producer}")
    private String topicName;

    @Override
    public Customer create(CustomerCommand customerCommand) {
        customerCommand.validate();
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));
        return customerRepository.save(Customer.create(customerCommand));
    }

    @Override
    public Page<Customer> findAllByDeletedFalse(Pageable pageable) {
        return customerRepository.findCustomersByDeletedFalse(pageable);
    }
    @Override
    public Customer findById(String customerId) {
        log.info("Begin fetching customer by id {}", customerId);
        final Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CUSTOMER_NOT_FOUND.get())
        );
        log.info("Customer with id {} fetched successfully", customer.getId());
        return customer;
    }

    @Override
    public Set<Driver> getDriversAvailable() {
        final ResponseEntity<Set<Driver>> objects = restTemplate.exchange(
                "http://DRIVER:8081/v1/drivers/available", HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Set<Driver>>(){});
        log.info("Object with payload {}", JSONUtil.toJSON(objects.getBody()));
        final Set<Driver> drivers = objects.getBody();
        return drivers;
    }
    @Override
    public String sendRequestDriver(CustomerRequestDriver requestDriver){
        final Driver driver = getDriversAvailable().stream().filter(
                dv -> dv.getId().equals(requestDriver.getDriverId()))
                .findAny().orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_LOCATION_NOT_FOUND.get())
        );
        log.info("Driver id {}", driver.getId());
        kafkaTemplate.send(topicName, driver.getId());
        return "Message send to driver id {} " + driver.getId();
    }

    @Override
    public void updateInfo(CustomerInfoUpdateCmd customerCommand, String customerId) {
        customerCommand.validate();
        final Customer customer = findById(customerId);
        customer.updateInfo(customerCommand);
        customerRepository.save(customer);
    }
}
