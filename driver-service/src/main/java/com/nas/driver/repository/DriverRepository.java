package com.nas.driver.repository;


import com.nas.core.util.PatternUtil;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.model.Driver;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.nas.driver.model.Driver_.DRIVER_STATUS;
import static com.nas.driver.model.Driver_.FIRST_NAME;


@Repository
public interface DriverRepository extends JpaRepository<Driver, String>, JpaSpecificationExecutor<Driver> {
    Page<Driver> findAllByDeletedFalse(Pageable pageable);
    Set<Driver> findByDriverStatusStatus(String status);



   default Page<Driver> findAllByCriteria(Pageable pageable, DriverCriteria driverCriteria){
        return findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(driverCriteria.firstName() != null){
                String pattern = PatternUtil.likePattern(driverCriteria.firstName().toUpperCase());
                predicates.add(builder.like(builder.upper(root.get(FIRST_NAME)), pattern));
            }
            if(driverCriteria.status() != null){
                String pattern = PatternUtil.likePattern(String.valueOf(driverCriteria.status()));
                predicates.add(builder.like(builder.upper(root.get(DRIVER_STATUS)), pattern));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }
}

