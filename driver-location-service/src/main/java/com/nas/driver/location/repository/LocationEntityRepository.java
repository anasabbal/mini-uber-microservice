package com.nas.driver.location.repository;

import com.nas.driver.location.model.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationEntityRepository extends JpaRepository<LocationEntity, String> {
}
