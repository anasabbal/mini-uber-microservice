package com.nas.driver.location.service;

import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.vividsolutions.jts.geom.Geometry;
import org.wololo.geojson.Feature;

public interface DriverLocationService {
    DriverLocation getOne(String driverLocationId);
    DriverLocation getById(String driverLocationId);
}
