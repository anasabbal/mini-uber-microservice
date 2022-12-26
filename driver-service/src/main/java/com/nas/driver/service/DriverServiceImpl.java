package com.nas.driver.service;


import com.nas.core.JSONUtil;
import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Customer;
import com.nas.driver.model.Driver;
import com.nas.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
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

        restTemplate.getForObject("http://localhost:8080/v1/customers/driver/{driverId}", Customer.class, driver.getId());

        return driver;
    }

    @Override
    public Driver update(String driverId, DriverCommand driverCommand) {
        return null;
    }
}
