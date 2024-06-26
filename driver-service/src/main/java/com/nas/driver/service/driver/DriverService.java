package com.nas.driver.service.driver;

import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.RatingCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.model.Driver;
import com.nas.driver.payload.DriverDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface DriverService {
    Driver create(final DriverCommand driverCommand);
    void update(String driverId, DriverCommand driverCommand);
    Page<Driver> getAll(Pageable pageable);
    Driver findById(String driverId);
    Set<Driver> getDriversAvailable();
    /*void listenToMessage(CustomerRequestDriver message);*/
    String sendRating(final RatingCommand ratingCommand);
    void deleteAccount(final String driverId);
    Page<Driver> findAllByCriteria(Pageable pageable, DriverCriteria driverCriteria);
    DriverDetails getDriverDetailsByDriverId(String driverId);
}