package com.nas.driver.location.controller;


import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.service.DriverLocationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.wololo.geojson.Feature;

import java.net.URI;

import static com.nas.core.ResourcePath.DRIVER_LOCATION;
import static com.nas.core.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + DRIVER_LOCATION)
public record DriverLocationController(DriverLocationService driverLocationService) {


    @GetMapping("/{driverId}")
    public ResponseEntity<DriverLocation> get(@PathVariable("driverId") final String driverId,
                                              @RequestBody Feature feature){
        return ResponseEntity.ok(driverLocationService.getOne(driverId, feature));
    }
    @PostMapping
    public ResponseEntity<DriverLocation> create(@RequestBody final DriverLocationCommand driverLocationCommand){
        final DriverLocation driverLocation = driverLocationService.create(driverLocationCommand);

        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(driverLocation.getId()).toUri();
        return ResponseEntity.created(uri).body(driverLocation);
    }
}
