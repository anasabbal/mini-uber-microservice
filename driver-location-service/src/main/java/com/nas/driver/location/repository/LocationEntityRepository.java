package com.nas.driver.location.repository;

import com.nas.driver.location.model.LocationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationEntityRepository extends MongoRepository<LocationEntity, String> {
}
