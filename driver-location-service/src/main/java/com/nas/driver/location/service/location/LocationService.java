package com.nas.driver.location.service.location;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.nas.driver.location.model.LocationEntity;

import java.io.IOException;

public interface LocationService {
    LocationEntity create() throws IOException, GeoIp2Exception;
}
