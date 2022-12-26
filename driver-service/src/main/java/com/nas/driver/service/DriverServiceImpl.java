package com.nas.driver.service;


import com.nas.core.JSONUtil;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;
import com.nas.driver.model.DriverLocationRequest;
import com.nas.driver.repository.DriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public record DriverServiceImpl(DriverRepository driverRepository, RestTemplate restTemplate) implements DriverService{

    @Override
    public Driver create(DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin creating driver with payload {}", JSONUtil.toJSON(driverCommand));
        final Driver driver = Driver.create(driverCommand);
        driverRepository.save(driver);
        log.info("Driver with id {} created successfully", driver.getId());
        restTemplate.getForObject(
                "http://localhost:8082/v1/driver-location/{driverLocationId}",
                DriverLocationRequest.class,
                driver.getId());
        return driver;
    }

    @Override
    public void update(String driverId, DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin updating driver with id {}", driverId);
        final Driver driver = driverRepository.findById(driverId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get())
        );
        log.info("Begin updating driver with payload {}", JSONUtil.toJSON(driverCommand));
        driver.updateInfo(driverCommand);
        log.info("Driver with id {} updated successfully", driver.getId());
    }
}
