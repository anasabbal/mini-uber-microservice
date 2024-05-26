package com.nas.customer.service.controller;


import com.nas.customer.service.dto.CustomerDto;
import com.nas.customer.service.command.CustomerCommand;
import com.nas.customer.service.command.CustomerInfoUpdateCmd;
import com.nas.customer.service.command.CustomerRequestDriver;
import com.nas.customer.service.command.RatingCommand;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.mapper.CustomerMapper;
import com.nas.customer.service.model.Customer;
import com.nas.customer.service.model.Driver;
import com.nas.customer.service.payload.CustomerDetails;
import com.nas.customer.service.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

import static com.nas.core.constants.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CUSTOMERS)
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;


    @PostMapping
    //@ApiOperation("API TO CREATE CUSTOMER WITH PAYLOAD CUSTOMERCOMMAND")
    public ResponseEntity<CustomerDto> create(@RequestBody final CustomerCommand customerCommand){
        final Customer customer = customerService.create(customerCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(customerMapper.toDto(customer));
    }
    @PostMapping(SEND_REQUEST)
    //@ApiOperation("API TO SEND REQUEST TO AVAILABLE DRIVER")
    public ResponseEntity<String> sendRequestToDriver(@RequestBody final CustomerRequestDriver customerRequestDriver){
        customerService.sendRequestDriver(customerRequestDriver);
        return ResponseEntity.ok("Message send successfully");
    }
    @GetMapping
    //@ApiOperation("API TO GET ALL CUSTOMERS")
    public ResponseEntity<Page<CustomerDto>> getAll(Pageable pageable){
        final Page<Customer> customers = customerService.findAllByDeletedFalse(pageable);
        return ResponseEntity.ok(customers.map(customerMapper::toDto));
    }
    @GetMapping(DRIVER_AVAILABLE)
    //@ApiOperation("API TO GET ALL DRIVER AVAILABLE")
    public ResponseEntity<Set<Driver>> getAllDriversAvailable(){
        return ResponseEntity.ok(customerService.getDriversAvailable());
    }
    @GetMapping("/{customerId}")
    //@ApiOperation("API TO GET CUSTOMER BY ID")
    public ResponseEntity<CustomerDto> getOne(@PathVariable("customerId") final String customerId){
        final Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }
    @PutMapping("/{customerId}")
    //@ApiOperation("API TO UPDATE CUSTOMER BY ID")
    public ResponseEntity<Void> update(@PathVariable("customerId") final String customerId,
                                       @RequestBody final CustomerInfoUpdateCmd command){
        customerService.updateInfo(command, customerId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(RATINGS)
    //@ApiOperation("API TO RATE SERVICE DRIVER")
    public ResponseEntity<String> sendRating(@RequestBody final RatingCommand ratingCommand){
        return ResponseEntity.ok(customerService.sendRating(ratingCommand));
    }
    @GetMapping(CRITERIA)
    //@ApiOperation("API TO GET CUSTOMER BY CRITERIA")
    public ResponseEntity<Page<CustomerDto>> getAllByCriteria(@RequestBody final CustomerCriteria customerCriteria, Pageable pageable){
        final Page<Customer> customers = customerService.getAllByCriteria(pageable, customerCriteria);
       return ResponseEntity.ok(customers.map(customerMapper::toDto));
    }
    @GetMapping(CUSTOMER_DETAILS + "/{customerId}")
    //@ApiOperation("API TO GET ALL DETAILS ABOUT CUSTOMER BY ID")
    public ResponseEntity<CustomerDetails> findCustomerDetailsByCustomerId(@PathVariable("customerId") final String customerId){
        return ResponseEntity.ok(customerService.findCustomerDetailsById(customerId));
    }
}
