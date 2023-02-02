package com.nas.driver.service.driver;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.enums.DriverStatus;
import com.nas.driver.model.Driver;
import com.nas.driver.model.DriverLocationRequest;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public record DriverServiceImpl(DriverRepository driverRepository,
                                RestTemplate restTemplate,
                                DriverMapper driverMapper,
                                NotificationDriverRepository notificationDriverRepository) implements DriverService{
    @Override
    public Driver create(DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin creating driver with payload {}", JSONUtil.toJSON(driverCommand));
        final Driver driver = Driver.create(driverCommand);
        driverRepository.save(driver);
        log.info("Driver with id {} created successfully", driver.getId());
        restTemplate.getForObject(
                "http://DRIVER-LOCATION:8082/v1/driver-location/{driverId}",
                DriverLocationRequest.class,
                driver.getId());
        restTemplate.getForEntity("http://PAYMENT:2345/v1/bank-account/{driverId}", String.class, driver.getBankAccountId());
        return driver;
    }
    @Override
    public void update(String driverId, DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin updating driver with id {}", driverId);
        final Driver driver =findById(driverId); 
        log.info("Begin updating driver with payload {}", JSONUtil.toJSON(driverCommand));
        driver.updateInfo(driverCommand);
        log.info("Driver with id {} updated successfully", driver.getId());
    }
    @Override
    public Set<Driver> getDriversAvailable(Pageable pageable) {
        return getAll(pageable).stream().filter(
                dv -> dv.getDriverStatus() == DriverStatus.AVAILABLE)
                .collect(Collectors.toSet());
    }
    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenToMessage(CustomerRequestDriver payload){
        log.info(String.format("Received message -> %s", JSONUtil.toJSON(payload)));
        final Driver driver = findById(payload.getDriverId());
        final NotificationDriver notificationDriver = NotificationDriver.create(payload);
        notificationDriver.setDriver(driver);
        notificationDriverRepository.save(notificationDriver);
    }
    @Override
    public Driver findById(String driverId){
      log.info("Begin fetching driver with id {}", driverId);
      final Driver driver = driverRepository.findById(driverId).orElseThrow(
          () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
      log.info("Driver with id {} fetched successfully", driverId);
      return driver;
    }
    @Override
    public Page<Driver> getAll(Pageable pageable) {
        return driverRepository.findAllByDeletedFalse(pageable);
    }
}
