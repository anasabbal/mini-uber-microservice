package com.nas.driver.location.repository;

import com.nas.driver.location.model.GeoIp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GeoIpRepository extends JpaRepository<GeoIp, String> {
}
