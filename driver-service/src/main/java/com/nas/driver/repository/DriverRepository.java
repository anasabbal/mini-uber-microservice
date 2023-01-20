package com.nas.driver.repository;

import com.nas.core.util.PatternUtil;
import com.nas.driver.criteria.DriverCriteria;
import com.nas.driver.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static com.nas.driver.model.Driver_.*;


@Repository
public interface DriverRepository extends JpaRepository<Driver, String> , JpaSpecificationExecutor<Driver> {
    default Page<Driver> findAllByDeletedFalse(Pageable pageable, DriverCriteria driverCriteria){
        return findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>(Arrays.asList(
                    builder.greaterThanOrEqualTo(root.get(FIRST_NAME), LocalDateTime.now())
            ));
            if (driverCriteria.getLastName() != null) {
                String pattern = PatternUtil.likePattern(driverCriteria.getLastName().toUpperCase());
                predicates.add(builder.like(builder.upper(root.get(LAST_NAME)), pattern));
            }
            if (driverCriteria.getStatus() != null) {
                predicates.add(builder.equal(root.get(ACTIVE), driverCriteria.getStatus()));
            }
            query.orderBy(builder.desc(root.get(DRIVER_STATUS)));
            return builder.and(predicates.toArray(new Predicate[]{}));

        }, pageable);
    }
}

