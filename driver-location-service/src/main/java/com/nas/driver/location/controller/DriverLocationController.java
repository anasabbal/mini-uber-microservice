package com.nas.driver.location.controller;


import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.service.DriverLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nas.core.ResourcePath.DRIVER_LOCATION;
import static com.nas.core.ResourcePath.V1;

@RestController
@RequestMapping(V1 + DRIVER_LOCATION)
public record DriverLocationController(DriverLocationService driverLocationService) {


    @GetMapping("/{driverId}")
    public ResponseEntity<DriverLocation> get(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(driverLocationService.getOne(driverId));
    }
}
