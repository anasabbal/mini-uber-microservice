package com.nas.customer.service.repository;

import com.nas.core.util.JSONUtil;
import com.nas.core.util.PatternUtil;
import com.nas.customer.service.criteria.CustomerCriteria;
import com.nas.customer.service.model.Customer;
import static com.nas.customer.service.model.Customer_.*;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> , JpaSpecificationExecutor<Customer> {
    public final static Logger log = LoggerFactory.getLogger(CustomerRepository.class);
    Page<Customer> findCustomersByDeletedFalse(Pageable pageable);
    Customer findByDriverId(String driverId);

    default Page<Customer> findAllByCriteria(Pageable pageable, CustomerCriteria customerCriteria){
        return findAll((root , query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (customerCriteria.firstName() != null) {
                String pattern = PatternUtil.likePattern(customerCriteria.firstName().toUpperCase());
                predicates.add(builder.like(builder.upper(root.get(FIRST_NAME)), pattern));
            }
            return builder.and(predicates.toArray(new Predicate[]{}));
        }, pageable);
    }
}