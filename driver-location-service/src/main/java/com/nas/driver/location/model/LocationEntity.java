package com.nas.driver.location.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mil.nga.sf.geojson.Feature;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;

@Document
@Getter
@Setter
@NoArgsConstructor
public class LocationEntity {

    @Id
    private String id;
    private Feature feature;

    public LocationEntity(LocationEntity locationEntity) {
        locationEntity = new LocationEntity();
    }
}
