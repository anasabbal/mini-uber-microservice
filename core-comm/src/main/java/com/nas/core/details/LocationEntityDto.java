package com.nas.core.details;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntityDto {

    protected String id;
    protected Boolean active;
    private GeoIp geoIp;
}

