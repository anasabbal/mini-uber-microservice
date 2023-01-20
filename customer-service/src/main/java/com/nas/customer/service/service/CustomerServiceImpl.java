package com.nas.customer.service.service;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.config.ProducerRabbitMqConfig;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.websocket.SendResult;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{
    
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public Customer create(CustomerCommand customerCommand) {
        customerCommand.validate();
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));

        return customerRepository.save(Customer.create(customerCommand));
    }

    @Override
    public Page<Customer> findAllByDeletedFalse(Pageable pageable, CustomerCriteria customerCriteria) {
        return customerRepository.findCustomersByDeletedFalse(pageable, customerCriteria);
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
                new ParameterizedTypeReference<>() {
                });
        log.info("Object with payload {}", JSONUtil.toJSON(objects.getBody()));
        return objects.getBody();
    }
    @Override
    public void sendRequestDriver(CustomerRequestDriver requestDriver){

     final Driver driver = getDriversAvailable().stream().filter(
                dv -> dv.getId().equals(requestDriver.getDriverId()))
                .findAny().orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_LOCATION_NOT_FOUND.get())
        );
        final Customer customer = findById(requestDriver.getCustomerId());
        log.info("Begin sending message");
        rabbitTemplate.convertAndSend("customer.exchange", "customer.routingkey", requestDriver);
        log.info("message send good");
    }
    @Override
    public void updateInfo(CustomerInfoUpdateCmd customerCommand, String customerId) {
        customerCommand.validate();
        final Customer customer = findById(customerId);
        customer.updateInfo(customerCommand);
        customerRepository.save(customer);
    }
}
