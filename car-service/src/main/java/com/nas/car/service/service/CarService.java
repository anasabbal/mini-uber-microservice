package com.nas.car.service.service;

import com.nas.car.service.command.CarCommand;
import com.nas.car.service.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    Car create(final CarCommand carCommand);
    Car findOne(String carId);
    Page<Car> findCars(Pageable pageable);
}
