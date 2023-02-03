package com.nas.customer.service.repository;

import com.nas.customer.service.model.NotificationCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationCustomerRepository extends JpaRepository<NotificationCustomer, String > {
}
