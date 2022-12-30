package com.nas.driver.service;

import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverService {
    Driver create(final DriverCommand driverCommand);
    void update(String driverId, DriverCommand driverCommand);
    Page<Driver> getAll(Pageable pageable);
}
