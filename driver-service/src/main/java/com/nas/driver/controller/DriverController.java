package com.nas.driver.controller;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;
import com.nas.driver.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.nas.core.ResourcePath.DRIVERS;
import static com.nas.core.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + DRIVERS)
public record DriverController(DriverService driverService) {


    @PostMapping
    public ResponseEntity<Driver> create(@RequestBody final DriverCommand driverCommand){
        final Driver driver = driverService.create(driverCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(driver);
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<Void> updateInfo(
            @PathVariable("driverId") final String driverId,
            @RequestBody final DriverCommand driverCommand){
        driverService.update(driverId, driverCommand);
        return ResponseEntity.noContent().build();
    }
}
