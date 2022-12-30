package com.nas.auth.service;

import com.nas.auth.model.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface CustomerServiceFeignClient {

    @GetMapping(value = "/v1/customers/{customerId}")
    Customer getCustomerById(@PathVariable String customerId);
}
