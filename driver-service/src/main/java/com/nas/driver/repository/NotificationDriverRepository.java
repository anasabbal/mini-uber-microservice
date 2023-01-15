package com.nas.driver.repository;

import com.nas.driver.model.NotificationDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationDriverRepository extends JpaRepository<NotificationDriver, String> {

    Page<NotificationDriver> findAllByDriverId(Pageable pageable, String driverId);
}
