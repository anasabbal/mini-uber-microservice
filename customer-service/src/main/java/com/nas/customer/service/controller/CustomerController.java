package com.nas.customer.service.controller;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.ResourcePath.CUSTOMERS;
import static com.nas.core.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;
import java.net.URI;

@RestController
@RequestMapping(V1 + CUSTOMERS)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody final CustomerCommand customerCommand){
        final Customer customer = customerService.create(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customer);
    }
}
