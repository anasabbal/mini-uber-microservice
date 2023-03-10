package com.nas.driver.service.driver;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.command.CustomerRequestDriver;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.command.RatingCommand;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.dto.mapper.DriverMapper;
import com.nas.driver.model.Driver;
import com.nas.driver.model.NotificationDriver;
import com.nas.driver.repository.DriverRepository;
import com.nas.driver.repository.NotificationDriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Set;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService{


    private final DriverRepository driverRepository;
    private final RestTemplate restTemplate;


    @Override
    public Driver create(DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("Begin creating driver with payload {}", JSONUtil.toJSON(driverCommand));
        final Driver driver = Driver.create(driverCommand);
        log.info("Driver with id {} created successfully", driver.getId());
        driverRepository.save(driver);
        final String uri = "http://DRIVER-LOCATION:8082/v1/driver-location/" + driver.getId();
        log.info("[+] URI => {}", uri);
        restTemplate.getForObject(
                uri,
                String.class,
                driver.getId());
        return driver;
    }
    @Override
    public void deleteAccount(String driverId) {
        log.info("[+] Begin removing account with id {}", driverId);
        final Driver driver = findById(driverId);
        driverRepository.delete(driver);
        restTemplate.delete("http://DRIVER-LOCATION:8082/v1/driver-location/" + driverId, driverId);
    }
    @Override
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listenToMessage(CustomerRequestDriver payload){
        log.info(String.format("[+] Received message -> %s", JSONUtil.toJSON(payload)));
        final Driver driver = findById(payload.getDriverId());
        log.info("[+] Begin creating notification for driver with id {}", driver.getId());
        driver.addToDriver(payload);
        driverRepository.save(driver);
    }

    @Override
    public Page<Driver> findAllByCriteria(Pageable pageable, DriverCriteria driverCriteria) {
        return driverRepository.findAllByCriteria(pageable, driverCriteria);
    }

    @Override
    public String sendRating(RatingCommand ratingCommand) {
        final Driver driver = findById(ratingCommand.getDriverId());
        final String customerId = driver.getLastNotification();
        if(ratingCommand.getCustomerId().equals(customerId)) {
            restTemplate.postForEntity(
                    "http://RATING:2018/v1/ratings/",
                    ratingCommand,
                    RatingCommand.class
            );
            return "Message Sent";
        }
        else{
            return "Message Not Sent";
        }
    }

    @Override
    public void update(String driverId, DriverCommand driverCommand) {
        driverCommand.validate();
        log.info("[+] Begin updating driver with id {}", driverId);
        final Driver driver =findById(driverId);
        log.info("[+] Begin updating driver with payload {}", JSONUtil.toJSON(driverCommand));
        driver.updateInfo(driverCommand);
        log.info("[+] Driver with id {} updated successfully", driver.getId());
        driverRepository.save(driver);
    }
    @Override
    public Set<Driver> getDriversAvailable() {
        return driverRepository.findByDriverStatusStatus("AVAILABLE");
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

