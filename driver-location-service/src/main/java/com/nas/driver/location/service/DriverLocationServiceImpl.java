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
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Slf4j
public record DriverLocationServiceImpl(
        DriverLocationRepository driverLocationRepository,
        LocationService locationService, RestTemplate restTemplate) implements DriverLocationService{

    @Override
    public DriverLocation getOne(String userId) throws IOException, GeoIp2Exception {

        final DriverLocationCommand driverLocationCommand = new DriverLocationCommand();
        driverLocationCommand.setName("Driver");

        final LocationEntity location = locationService.create();

        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setDriverId(userId);
        driverLocation.addToSet(location);
        driverLocationRepository.save(driverLocation);
        restTemplate.getForObject(
                "http://PAYMENT:2345/v1/payment/" + userId, String.class,  userId
        );
        return driverLocation;
    }

    @Override
    public void deleteDriverLocationByDriverId(String driverId) {
        log.info("[+] Begin fetching Driver Location with id {}", driverId);
        final DriverLocation driverLocation = driverLocationRepository.findDriverLocationByDriverId(driverId);
        driverLocationRepository.delete(driverLocation);
        restTemplate.delete("http://BANK-ACCOUNT:2345/v1/bank-account/user/" + driverId, driverId);
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
