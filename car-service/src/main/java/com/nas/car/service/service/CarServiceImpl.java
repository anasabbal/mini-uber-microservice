package com.nas.car.service.service;


import com.nas.car.service.command.CarCommand;
import com.nas.car.service.model.Car;
import com.nas.car.service.repository.CarRepository;
import com.nas.core.util.JSONUtil;
import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayloadFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService{


    private final CarRepository carRepository;


    @Override
    public Car create(CarCommand carCommand) {
        carCommand.validate();
        log.info("Begin creating car with payload {}", JSONUtil.toJSON(carCommand));
        return carRepository.save(Car.create(carCommand));
    }

    @Override
    public Car findOne(String carId) {
        log.info("Begin fetching car with id {}", carId);
        return carRepository.findById(carId).orElseThrow(
                () -> new BusinessException(ExceptionPayloadFactory.CAR_NOT_FOUND.get())
        );
    }

    @Override
    public List<Car> getCars() {
        return (List<Car>) carRepository.findAll();
    }
}
