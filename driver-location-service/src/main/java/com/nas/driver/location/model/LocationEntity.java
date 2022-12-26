package com.nas.driver.location.model;


import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("LOCATION_ENTITY")
@Data
public class LocationEntity {

    private String id;
    private Geometry geometry;
}
