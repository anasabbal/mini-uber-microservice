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
        driverLocation.getLocationEntities().add(location);
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
    /*@Override
    public LocationEntity convertFeatureToEntity(Feature feature) {
        LocationEntity entity = new LocationEntity();
        Map<String, Object> propertiesList = feature.getProperties();
        Arrays.stream(LocationEntity.class.getDeclaredFields())
                .filter(i -> !i.isSynthetic())
                .forEach(i -> {
                    try {
                        Field f = LocationEntity.class.getDeclaredField(i.getName());
                        f.setAccessible(true);
                        f.set(entity, propertiesList.getOrDefault(i.getName(), null));
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        log.warn(e.getMessage());
                    }
                });
        entity.setGeometry(convertGeoJsonToJtsGeometry(feature.getGeometry()));
        return entity;
    }
    private Feature convertEntityToFeature(LocationEntity entity){
        org.wololo.geojson.Geometry geometry = convertJtsGeometryToGeoJson(entity.getGeometry());

        Map<String , Object> properties = new HashMap<>();

        Arrays.stream(LocationEntity.class.getDeclaredFields())
                .filter(i -> !i.isSynthetic())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        if (field.getType() != Geometry.class && !field.getName().equals("id") && !field.getName().equals("user")) {
                            properties.put(field.getName(), field.get(entity));
                        }
                    } catch (IllegalAccessException e) {
                        log.warn(e.getMessage());
                    }
                });

        return new Feature(entity.getId(), geometry, properties);
    }
    public org.wololo.geojson.Geometry convertJtsGeometryToGeoJson(Geometry geometry) {
        return new GeoJSONWriter().write(geometry);
    }
    @Override
    public Geometry convertGeoJsonToJtsGeometry(org.wololo.geojson.Geometry geoJson) {
        return new GeoJSONReader().read(geoJson);
    }*/
}
