package com.nas.driver.controller;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.dto.DriverDto;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.service.driver.DriverService;
import com.nas.driver.service.notification.NotificationDriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

import static com.nas.core.constants.ResourcePath.DRIVERS;
import static com.nas.core.constants.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + DRIVERS)
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    private final DriverMapper driverMapper;
    private final NotificationDriverService notificationDriverService;

    @PostMapping
    public ResponseEntity<DriverDto> create(@RequestBody final DriverCommand driverCommand){
        final Driver driver = driverService.create(driverCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(uri).body(driverMapper.toDto(driver));
    }
    @GetMapping("/available")
    public ResponseEntity<Set<Driver>> getAllAvailable(Pageable pageable){
        return ResponseEntity.ok(driverService.getDriversAvailable(pageable));
    }
    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriverById(@PathVariable("driverId") final String driverId){
        return ResponseEntity.ok(driverService.findById(driverId));
    }
    @GetMapping
    public ResponseEntity<Page<Driver>> getAll(Pageable pageable){
        return ResponseEntity.ok(driverService.getAll(pageable));
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<Void> updateInfo(
            @PathVariable("driverId") final String driverId,
            @RequestBody final DriverCommand driverCommand){
        driverService.update(driverId, driverCommand);
        return ResponseEntity.noContent().build();
    }
}
