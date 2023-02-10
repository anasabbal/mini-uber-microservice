package com.nas.order.repository;


import com.nas.order.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface OrderRepository<T extends OrderEntity> extends JpaRepository<T, String> {
}
