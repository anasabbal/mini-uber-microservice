package com.nas.driver.repository;

import com.nas.driver.model.NotificationDriver;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationDriverRepository extends MongoRepository<NotificationDriver, String> {
}
