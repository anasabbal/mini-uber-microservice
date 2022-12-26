package com.nas.car.service;


import com.nas.car.service.command.CarCommand;
import com.nas.car.service.enums.CarType;
import com.nas.car.service.model.Car;
import com.nas.car.service.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories(value="com.nas.car.service.repository")
public class CarServiceApplication implements CommandLineRunner {


    @Autowired
    private CarRepository carRepository;


    public static void main(String[] args) {
        SpringApplication.run(CarServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        CarCommand carCommand = new CarCommand();
        carCommand.setCarType(CarType.NAS_ELECT);

        carRepository.save(Car.create(carCommand));
    }
}
