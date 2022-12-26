package com.nas.driver.repository;

import com.nas.driver.model.Driver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends MongoRepository<Driver, String> {
}
