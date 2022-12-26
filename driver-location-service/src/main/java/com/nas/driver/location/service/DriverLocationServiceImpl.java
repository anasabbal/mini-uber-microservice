package com.nas.driver.location.service;


import com.nas.driver.location.command.DriverLocationCommand;
import com.nas.driver.location.model.DriverLocation;
import com.nas.driver.location.model.LocationEntity;
import com.nas.driver.location.repository.DriverLocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.wololo.geojson.Feature;
import org.springframework.stereotype.Service;
import com.vividsolutions.jts.geom.Geometry;
import org.wololo.jts2geojson.GeoJSONWriter;
import org.wololo.jts2geojson.GeoJSONReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public record DriverLocationServiceImpl(
        DriverLocationRepository driverLocationRepository) implements DriverLocationService{

    @Override
    public DriverLocation create(final DriverLocationCommand driverLocationCommand){
        final Feature feature = driverLocationCommand.getFeature();
        LocationEntity location = convertFeatureToEntity(feature);
        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setLocationId(location.getId());
        return driverLocationRepository.save(driverLocation);
    }
    @Override
    public DriverLocation getOne(String driverLocationId, Feature feature){

        final DriverLocationCommand driverLocationCommand = new DriverLocationCommand();
        driverLocationCommand.setName("driverLocation 1");

        LocationEntity location = convertFeatureToEntity(feature);
        final DriverLocation driverLocation = DriverLocation.create(driverLocationCommand);
        driverLocation.setDriverId(driverLocationId);
        driverLocation.setLocationId(location.getId());
        return driverLocationRepository.save(driverLocation);
    }

    @Override
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
    }
}
