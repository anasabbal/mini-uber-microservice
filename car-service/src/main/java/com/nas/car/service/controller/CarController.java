package com.nas.car.service.controller;


import com.nas.car.service.command.CarCommand;
import com.nas.car.service.model.Car;
import com.nas.car.service.service.CarService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.nas.core.constants.ResourcePath.CARS;
import static com.nas.core.constants.ResourcePath.V1;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping(V1 + CARS)
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    @ApiOperation(value = "API TO CREATE A CAR")
    public ResponseEntity<Car> create(@RequestBody final CarCommand carCommand){
        final Car car = carService.create(carCommand);
        final URI uri = fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
        return ResponseEntity.created(uri).body(car);
    }

    @GetMapping("/{carId}")
    @ApiOperation(value = "API TO GET CAR BY ID")
    public ResponseEntity<Car> getOne(@PathVariable("carId") final String carId){
        return ResponseEntity.ok().body(carService.findOne(carId));
    }
    @GetMapping
    @ApiOperation(value = "GET ALL CARS BY DELETED FALSE")
    public ResponseEntity<Page<Car>> getCars(Pageable pageable){
        return ResponseEntity.ok(carService.findCars(pageable));
    }
}
