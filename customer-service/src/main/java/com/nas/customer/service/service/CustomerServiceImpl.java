package com.nas.customer.service.service;


import com.nas.core.JSONUtil;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(CustomerCommand customerCommand) {
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));

        return customerRepository.save(Customer.create(customerCommand));
    }
}
