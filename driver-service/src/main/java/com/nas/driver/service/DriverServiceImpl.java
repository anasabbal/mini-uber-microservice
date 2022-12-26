package com.nas.driver.service;


import com.nas.driver.command.DriverCommand;
import com.nas.driver.model.Driver;
import com.nas.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverServiceImpl implements DriverService{


    private final DriverRepository driverRepository;


    @Override
    public Driver create(DriverCommand driverCommand) {
        return null;
    }

    @Override
    public Driver update(String driverId, DriverCommand driverCommand) {
        return null;
    }
}
