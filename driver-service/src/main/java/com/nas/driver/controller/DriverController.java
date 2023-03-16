package com.nas.driver.controller;


import com.nas.driver.command.AcceptRequestCustomer;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.RatingCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.details.DriverDetails;
import com.nas.driver.dto.DriverDto;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.model.Driver;
import com.nas.driver.service.driver.DriverService;
import com.nas.driver.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;
import java.util.stream.Collectors;

import static com.nas.core.constants.ResourcePath.*;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + DRIVERS)
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<DriverDto> create(@RequestBody final DriverCommand driverCommand){
        final Driver driver = driverService.create(driverCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(driverMapper.toDto(driver));
    }
    @GetMapping(AVAILABLE)
    public ResponseEntity<Set<DriverDto>> getAllAvailable(){
        return ResponseEntity.ok(driverService.getDriversAvailable()
                .stream().map(driverMapper::toDto)
                .collect(Collectors.toSet()));
    }
    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable("driverId") final String driverId){
        final Driver driver = driverService.findById(driverId);
        return ResponseEntity.ok(driverMapper.toDto(driver));
    }
    @GetMapping
    public ResponseEntity<Page<DriverDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(driverService.getAll(pageable).map(driverMapper::toDto));
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<Void> updateInfo(
            @PathVariable("driverId") final String driverId,
            @RequestBody final DriverCommand driverCommand){
        driverService.update(driverId, driverCommand);
        return ResponseEntity.noContent().build();
    }
    @PostMapping(RATINGS)
    public ResponseEntity<String> sendRating(@RequestBody final RatingCommand ratingCommand){
        return ResponseEntity.ok(driverService.sendRating(ratingCommand));
    }
    @PutMapping(CANCEL)
    public ResponseEntity<DriverDto> cancelRequest(@RequestBody final AcceptRequestCustomer acceptRequestCustomer){
        final Driver driver = notificationService.cancelRequest(acceptRequestCustomer);
        return ResponseEntity.ok(driverMapper.toDto(driver));
    }
    @GetMapping(CRITERIA)
    public ResponseEntity<Page<DriverDto>> getByCriteria(@RequestBody final DriverCriteria driverCriteria, Pageable pageable){
        final Page<Driver> drivers = driverService.findAllByCriteria(pageable, driverCriteria);
        return ResponseEntity.ok(drivers.map(driverMapper::toDto));
    }
    @GetMapping(DRIVER_DETAILS + "/{driverId}")
    public ResponseEntity<DriverDetails> getDriverDetailsByDriverId(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(driverService.findDriverDetailsByDriverId(driverId));
    }
}
