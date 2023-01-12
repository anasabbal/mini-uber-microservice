package com.nas.driver.service;

import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface DriverService {
    Driver create(final DriverCommand driverCommand);
    void update(String driverId, DriverCommand driverCommand);
    Page<Driver> getAll(Pageable pageable);
    Driver findById(String driverId);
    Set<Driver> getDriversAvailable(Pageable pageable);
    void listenWhiteHeader(ConsumerRecord<String, NotificationDriver> payload);
}
