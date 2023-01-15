package com.nas.driver.repository;

import com.nas.driver.model.NotificationDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationDriverRepository extends JpaRepository<NotificationDriver, String> {
}
