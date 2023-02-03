package com.nas.driver.repository;


import com.nas.driver.enums.DriverStatus;
import com.nas.driver.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface DriverRepository extends JpaRepository<Driver, String>{
    Page<Driver> findAllByDeletedFalse(Pageable pageable);
    Set<Driver> findByDriverStatusStatus(String status);
}

