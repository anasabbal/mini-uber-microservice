package com.nas.customer.service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BaseService<T>{
    <P> T create(P payload, Class<T> eClass);
    T findById(String id);
    <T> void updateById(String id, T payload);
    Page<T> getAllByDeletedFalse(Pageable pageable);
}
