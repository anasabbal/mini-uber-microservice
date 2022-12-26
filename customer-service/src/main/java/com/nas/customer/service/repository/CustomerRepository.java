package com.nas.customer.service.repository;

import com.nas.customer.service.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Page<Customer> findCustomersByDeletedFalse(Pageable pageable);
    Customer findByDriverId(String driverId);
}
