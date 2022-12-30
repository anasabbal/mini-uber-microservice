package com.nas.customer.service.controller;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.nas.core.ResourcePath.CUSTOMERS;
import static com.nas.core.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

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
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<Customer> get(@PathVariable final String driverId){
        return ResponseEntity.ok(customerService.get(driverId));
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAll(Pageable pageable){
        final Page<Customer> customers = customerService.findAllByDeletedFalse(pageable);
        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getOne(@PathVariable("customerId") final String customerId){
        final Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customer);
    }
}
