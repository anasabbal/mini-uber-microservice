package com.nas.driver.location.service;


import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.nas.driver.location.repository.DriverLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public record DriverLocationServiceImpl(
        DriverLocationRepository driverLocationRepository,
        LocationService locationService) implements DriverLocationService{

    @Override
    public DriverLocation getOne(String driverLocationId) throws IOException, GeoIp2Exception {

        final DriverLocationCommand driverLocationCommand = new DriverLocationCommand();
        driverLocationCommand.setName("Driver");

        final LocationEntity location = locationService.create();

        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setDriverId(driverLocationId);
        driverLocation.addToSet(location);
        return driverLocationRepository.save(driverLocation);
    }

    @Override
    public Page<DriverLocation> getAll(Pageable pageable) {
        return driverLocationRepository.findAll(pageable);
    }

    @Override
    public DriverLocation getById(String driverLocationId){
        log.info("Begin fetching Driver Location with id {}", driverLocationId);
        return driverLocationRepository.findById(driverLocationId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_LOCATION_NOT_FOUND.get())
        );
    }
}
