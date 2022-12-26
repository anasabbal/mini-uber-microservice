package com.nas.driver.location.service;

import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.vividsolutions.jts.geom.Geometry;
import org.wololo.geojson.Feature;

public interface DriverLocationService {
    DriverLocation getOne(String driverLocationId, Feature feature);
    DriverLocation create(final DriverLocationCommand driverLocationCommand);
    LocationEntity convertFeatureToEntity(Feature feature);
    Geometry convertGeoJsonToJtsGeometry(org.wololo.geojson.Geometry geoJson);
}
