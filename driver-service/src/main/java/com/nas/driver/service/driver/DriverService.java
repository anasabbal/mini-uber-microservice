package com.nas.driver.service.driver;

import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface DriverService {
    Driver create(final DriverCommand driverCommand);
    void update(String driverId, DriverCommand driverCommand);
    Page<Driver> getAll(Pageable pageable, DriverCriteria driverCriteria);
    Driver findById(String driverId);
    Set<Driver> getDriversAvailable(Pageable pageable, DriverCriteria driverCriteria);
    void listenToMessage(CustomerRequestDriver message);
}
