package com.nas.driver.location.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.driver.location.model.DriverLocation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface DriverLocationService {
    DriverLocation getOne(String driverLocationId) throws IOException, GeoIp2Exception;
    DriverLocation getById(String driverLocationId);
    Page<DriverLocation> getAll(Pageable pageable);
    void deleteDriverLocationByDriverId(String driverId);
}
