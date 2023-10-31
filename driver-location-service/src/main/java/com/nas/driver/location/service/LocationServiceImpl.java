package com.nas.driver.location.service;


import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import com.nas.core.util.JSONUtil;
import com.nas.driver.location.model.GeoIp;
import com.nas.driver.location.model.LocationEntity;
import com.nas.driver.location.repository.GeoIpRepository;
import com.nas.driver.location.repository.LocationEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;


@Service
@Slf4j
public record LocationServiceImpl(
        LocationEntityRepository locationEntityRepository,
        GeoIpRepository geoIpRepository) implements LocationService{

    @Override
    public LocationEntity create() throws IOException, GeoIp2Exception {
        final LocationEntity location = new LocationEntity();
        String ipAddress = getIpAddress();
        log.info("IP ADDRESS => {}", ipAddress);
        final GeoIp geoIp = getLocation("128.101.101.101");

        location.setGeoIp(geoIp);
        geoIpRepository.save(geoIp);
        log.info("Location with payload {} saved successfully", JSONUtil.toJSON(location));
        return locationEntityRepository.save(location);
    }
    private String getIpAddress() throws IOException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String _inetAddress = InetAddress.getLocalHost().getHostAddress().trim();
        log.info("Your current IP Address : {}", _inetAddress);
        String hostName = inetAddress.getHostName();
        log.info("Your current host name :{} ", hostName);

        return _inetAddress;
    }
    public LocationEntity getById(String locationId){
        return locationEntityRepository.findById(locationId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.LOCATION_NOT_FOUND.get())
        );
    }
    private GeoIp getLocation(String ip) throws IOException, GeoIp2Exception {
        /*InetAddress inetAddress = InetAddress.getByName(ip);
        CityResponse response = databaseReader.city(inetAddress);

        String cityName = response.getCity().getName();
        log.info("City => {}", cityName);
        String country = response.getCountry().getName();
        log.info("Country => {}", country);
        String latitude = response.getLocation().getLatitude().toString();
        log.info("Latitude => {}", latitude);
        String longitude = response.getLocation().getLongitude().toString();
        log.info("Longitude => {}", longitude);
        return new GeoIp(country, ip, cityName, latitude, longitude);*/
        return null;
    }
}
