package com.nas.driver.location.service;


import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.repository.DriverLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public record DriverLocationServiceImpl(
        DriverLocationRepository driverLocationRepository) implements DriverLocationService{


    @Override
    public DriverLocation getOne(String driverLocationId){

        final DriverLocationCommand driverLocationCommand = new DriverLocationCommand();
        driverLocationCommand.setName("driverLocation 1");
        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setDriverId(driverLocationId);
        return driverLocationRepository.save(driverLocation);
    }
}
