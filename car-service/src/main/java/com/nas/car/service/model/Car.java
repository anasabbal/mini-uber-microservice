package com.nas.car.service.model;


import com.nas.car.service.command.CarCommand;
import com.nas.car.service.enums.CarType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Setter
@RedisHash("CARS")
public class Car {

    private String id;
    private CarType carType;
    private Integer max_cars = 30;

    public static Car create(final CarCommand carCommand){
        final Car car = new Car();

        car.carType = carCommand.getCarType();

        return car;
    }
}
