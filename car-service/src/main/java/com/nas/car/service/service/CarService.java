package com.nas.car.service.service;

import com.nas.car.service.command.CarCommand;
import com.nas.car.service.model.Car;

import java.util.List;

public interface CarService {
    Car create(final CarCommand carCommand);
    Car findOne(String carId);
    List<Car> getCars();
}
