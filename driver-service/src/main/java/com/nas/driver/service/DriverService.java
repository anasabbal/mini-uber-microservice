package com.nas.driver.service;

import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;

public interface DriverService {
    Driver create(final DriverCommand driverCommand);
    Driver update(String driverId, DriverCommand driverCommand);
}
