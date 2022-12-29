package com.nas.driver.location.service;


import com.nas.core.JSONUtil;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.driver.location.command.DriverCommandUpdate;
import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.nas.driver.location.repository.DriverLocationRepository;
import com.nas.driver.location.repository.LocationEntityRepository;
import lombok.extern.slf4j.Slf4j;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.Geometry;
import mil.nga.sf.geojson.GeometryType;
import org.springframework.stereotype.Service;
import org.wololo.jts2geojson.GeoJSONWriter;
import org.wololo.jts2geojson.GeoJSONReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public record DriverLocationServiceImpl(
        DriverLocationRepository driverLocationRepository,
        LocationEntityRepository locationEntityRepository) implements DriverLocationService{

    @Override
    public DriverLocation getOne(String driverLocationId){

        final DriverLocationCommand driverLocationCommand = new DriverLocationCommand();
        driverLocationCommand.setName("driverLocation 1");
        final LocationEntity location = createLocationEntity();
        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setDriverId(driverLocationId);
        Set<LocationEntity> locationEntitySet = driverLocation.getLocationEntities();
        locationEntitySet.add(location);
        return driverLocationRepository.save(driverLocation);
    }

    @Override
    public DriverLocation getById(String driverLocationId){
        log.info("Begin fetching Driver Location with id {}", driverLocationId);
        return driverLocationRepository.findById(driverLocationId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.DRIVER_LOCATION_NOT_FOUND.get())
        );
    }

    private LocationEntity createLocationEntity(){
        final LocationEntity location = new LocationEntity();
        final mil.nga.sf.geojson.Geometry geometry = new Geometry() {
            @Override
            public GeometryType getGeometryType() {
                return GeometryType.POINT;
            }

            @Override
            public mil.nga.sf.Geometry getGeometry() {
                return this.getGeometry();
            }
        };
        mil.nga.sf.geojson.Feature feature1 = new Feature();
        feature1.setGeometry(geometry);
        locationEntityRepository.save(location);
        return location;

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
