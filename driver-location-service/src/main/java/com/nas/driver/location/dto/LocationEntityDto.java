package com.nas.driver.location.dto;

import com.nas.driver.location.model.GeoIp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationEntityDto {
    protected String id;
    protected Boolean active;
    private GeoIp geoIp;
}
