package com.nas.driver.location.repository;

import com.nas.driver.location.model.DriverLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverLocationRepository extends JpaRepository<DriverLocation, String> {
}
