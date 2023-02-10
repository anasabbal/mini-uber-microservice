package com.nas.order.service;

import com.nas.core.exception.ExceptionPayload;
import com.nas.order.data.UserData;

import java.util.List;

public interface OrderService<T> {

    T findOrderById(String id, ExceptionPayload exceptionPayload);

    List<T> findOrders();

    <C extends UserData> T createOrder(C command, Class<T> clazz);
}
