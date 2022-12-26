package com.nas.driver.location.repository;

import com.nas.driver.location.model.DriverLocation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverLocationRepository extends MongoRepository<DriverLocation, String> {
}
