package com.nas.car.service.repository;

import com.nas.car.service.model.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends CrudRepository<Car, String> {
}
