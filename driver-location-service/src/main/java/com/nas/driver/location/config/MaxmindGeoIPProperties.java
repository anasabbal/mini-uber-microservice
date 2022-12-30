package com.nas.driver.location.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties(prefix = MaxmindGeoIPProperties.PROPERTY_PREFIX)
public class MaxmindGeoIPProperties {

    public static final String PROPERTY_PREFIX = "maxmind.geoip";

    private Boolean enabled = true;

    private Resource geolite2CountryMmdb;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Resource getGeolite2CountryMmdb() {
        return geolite2CountryMmdb;
    }

    public void setGeolite2CountryMmdb(Resource geolite2CountryMmdb) {
        this.geolite2CountryMmdb = geolite2CountryMmdb;
    }

}
