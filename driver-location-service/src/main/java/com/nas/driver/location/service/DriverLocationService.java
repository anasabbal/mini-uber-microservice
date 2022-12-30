package com.nas.driver.location.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.driver.location.model.DriverLocation;

import java.io.IOException;

public interface DriverLocationService {
    DriverLocation getOne(String driverLocationId) throws IOException, GeoIp2Exception;
    DriverLocation getById(String driverLocationId);
}
