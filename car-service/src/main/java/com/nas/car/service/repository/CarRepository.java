package com.nas.car.service.repository;

import com.nas.car.service.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Page<Car> findAllByDeletedFalse(Pageable pageable);
}
