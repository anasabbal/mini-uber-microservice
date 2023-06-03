package com.nas.order.service;


import com.nas.core.exception.BusinessException;
import com.nas.core.exception.ExceptionPayload;
import com.nas.core.util.JSONUtil;
import com.nas.order.data.UserData;
import com.nas.order.models.OrderEntity;
import com.nas.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl<T extends OrderEntity> implements OrderService<T>{

    private final OrderRepository<T> orderRepository;

    @Override
    public T findOrderById(String id, ExceptionPayload exceptionPayload) {
        log.info("Begin fetching order with id {}", id);
        final T entity = orderRepository.findById(id).orElseThrow(
                () -> new BusinessException(exceptionPayload)
        );
        log.info("[+} Order with id {} fetched successfully", JSONUtil.toJSON(entity));
        return orderRepository.save(entity);
    }

    @Override
    public List<T> findOrders() {
        return orderRepository.findAll();
    }

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public <C extends UserData> T createOrder(C command, Class<T> clazz) {
        log.info("Begin creating object with payload {}", JSONUtil.toJSON(command));
        final T entity = clazz.getDeclaredConstructor().newInstance();
        entity.create(command);
        return orderRepository.save(entity);
    }
}
