package com.nas.customer.service.controller;


import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.dto.CustomerDto;
import com.nas.customer.service.dto.mapper.CustomerMapper;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.model.DriverSet;
import com.nas.customer.service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

import static com.nas.core.constants.ResourcePath.CUSTOMERS;
import static com.nas.core.constants.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CUSTOMERS)
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping
    public ResponseEntity<CustomerDto> create(@RequestBody final CustomerCommand customerCommand){
        final Customer customer = customerService.create(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customerMapper.toDto(customer));
    }
    @GetMapping
    public ResponseEntity<Page<CustomerDto>> getAll(Pageable pageable){
        final Page<Customer> customers = customerService.findAllByDeletedFalse(pageable);
        return ResponseEntity.ok(customers.map(customerMapper::toDto));
    }
    @GetMapping("/driver/available")
    public ResponseEntity<Set<Driver>> getAllDriversAvailable(Pageable pageable){
        return ResponseEntity.ok(customerService.getDriversAvailable(pageable));
    }
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getOne(@PathVariable("customerId") final String customerId){
        final Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }
    @PutMapping("/{customerId}")
    public ResponseEntity<Void> update(@PathVariable("customerId") final String customerId,
                                       @RequestBody final CustomerInfoUpdateCmd command){
        customerService.updateInfo(command, customerId);
        return ResponseEntity.noContent().build();
    }
}
