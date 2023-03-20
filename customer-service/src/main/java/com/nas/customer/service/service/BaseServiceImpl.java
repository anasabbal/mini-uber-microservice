package com.nas.customer.service.service;

import com.nas.customer.service.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BaseServiceImpl<T> implements BaseService<T>{

    private final BaseRepository<T> baseRepository;


    @Override
    public <P> T create(P payload, Class<T> eClass) {
        return null;
    }

    @Override
    public T findById(String id) {
        return null;
    }

    @Override
    public <T1> void updateById(String id, T1 payload) {

    }

    @Override
    public Page<T> getAllByDeletedFalse(Pageable pageable) {
        return null;
    }
}
