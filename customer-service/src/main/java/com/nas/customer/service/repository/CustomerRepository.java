package com.nas.customer.service.repository;

import com.nas.core.util.PatternUtil;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.model.Customer;
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

import static com.nas.customer.service.model.BaseEntity_.ACTIVE;
import static com.nas.customer.service.model.Customer_.FIRST_NAME;
import static com.nas.customer.service.model.Customer_.LAST_NAME;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> , JpaSpecificationExecutor<Customer> {
    default Page<Customer> findCustomersByDeletedFalse(Pageable pageable, CustomerCriteria customerCriteria){
        return findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>(Arrays.asList(
                    builder.greaterThanOrEqualTo(root.get(FIRST_NAME), LocalDateTime.now())
            ));
            if (customerCriteria.getLastName() != null) {
                String pattern = PatternUtil.likePattern(customerCriteria.getLastName().toUpperCase());
                predicates.add(builder.like(builder.upper(root.get(LAST_NAME)), pattern));
            }
            if (customerCriteria.getActive() != null) {
                predicates.add(builder.equal(root.get(ACTIVE), customerCriteria.getActive()));
            }
            query.orderBy(builder.desc(root.get(ACTIVE)));
            return builder.and(predicates.toArray(new Predicate[]{}));

        }, pageable);
    }
    Customer findByDriverId(String driverId);
}
