package com.nas.driver.location.controller;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.driver.location.dto.DriverLocationDto;
import com.nas.driver.location.dto.mapper.DriverLocationMapper;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.service.DriverLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.nas.core.constants.ResourcePath.DRIVER_LOCATION;
import static com.nas.core.constants.ResourcePath.V1;

@RestController
@RequestMapping(V1 + DRIVER_LOCATION)
public record DriverLocationController(DriverLocationService driverLocationService, DriverLocationMapper driverLocationMapper) {

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverLocationDto> get(@PathVariable("driverId") final String driverId) throws IOException, GeoIp2Exception {
        return ResponseEntity.ok(driverLocationMapper.toDto(driverLocationService.getOne(driverId)));
    }
    @GetMapping
    public ResponseEntity<Page<DriverLocationDto>> getAll(Pageable pageable){
        return ResponseEntity.ok(driverLocationService.getAll(pageable).map(driverLocationMapper::toDto));
    }
}
