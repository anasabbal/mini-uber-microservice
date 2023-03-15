package com.nas.driver.location.controller;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.driver.location.dto.DriverLocationDto;
import com.nas.driver.location.dto.mapper.DriverLocationMapper;
import com.nas.driver.location.service.DriverLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.nas.core.constants.ResourcePath.*;

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
    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> delete(@PathVariable("driverId")final String driverId){
        driverLocationService.deleteDriverLocationByDriverId(driverId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping(DRIVER_LOCATION_DETAILS + "/{driverId}")
    public ResponseEntity<DriverLocationDto> findDriverLocationByDriverId(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(driverLocationMapper.toDto(driverLocationService.findDriverLocationByDriverId(driverId)));
    }
}
