package com.nas.driver.service;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.enums.DriverStatus;
import com.nas.driver.model.Driver;
import com.nas.driver.model.DriverLocationRequest;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public Driver findById(String driverId){
      log.info("Begin fetching driver with id {}", driverId);
      final Driver driver = driverRepository.findById(driverId).orElseThrow(
          () -> new BusinessException(ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()));
      log.info("Driver with id {} fetched successfully", driverId);
      return driver;
    }
    @Override
    public Page<Driver> getAll(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }
    @Override
    @KafkaListener(id = "driver_id", topics = "topic1")
    public void listenWhiteHeader(ConsumerRecord<String, String> payload){

        log.info("Topic: {}", payload.topic());
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Part: {}", payload.partition());
        log.info("Payload: {}", payload.value());

        final CustomerRequestDriver customerRequestDriver = CustomerRequestDriver.create(payload.value());

        // Store notificationDriver from payload
        final NotificationDriver notificationDriver = notificationDriverRepository.save(
                NotificationDriver.create(customerRequestDriver));

        // Get driver with id
        final Driver driver = driverRepository.findById(notificationDriver.getDriverId()).orElseThrow(
                () -> new BusinessException(
                        ExceptionPayloadFactory.DRIVER_NOT_FOUND.get()
                )
        );
        log.info("Begin init notification with id {} to driver with id {}", notificationDriver.getId(), driver.getId());
        driver.addToSet(notificationDriver);
        log.info("Notification with id {} init ro driver with id {}", notificationDriver.getId(), driver.getId());
        log.info("Notifications with payload {} for driver id {}", JSONUtil.toJSON(driver.getNotificationDriversId()), driver.getId());
        log.info("Notification created successfully with payload {}", JSONUtil.toJSON(notificationDriver));
    }
}
