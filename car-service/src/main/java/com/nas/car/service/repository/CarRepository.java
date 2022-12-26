package com.nas.car.service.repository;

import com.nas.car.service.model.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends MongoRepository<Car, String> {
}
