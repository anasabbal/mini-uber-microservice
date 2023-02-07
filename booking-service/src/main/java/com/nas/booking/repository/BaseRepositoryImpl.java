package com.nas.booking.repository;

import com.nas.booking.command.BookCommand;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.util.Map;





@Slf4j
@Repository
@RequiredArgsConstructor
public class BaseRepositoryImpl<E> implements BaseRepository<String, E>{

    private final RedisTemplate<String, E> redisTemplate;
    private HashOperations hashOperations;


    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Map<String, E> findAll() throws InstantiationException, IllegalAccessException {
        return hashOperations.@(getParameterTypeName(), null);
    }

    @SneakyThrows
    @Override
    public <C extends BookCommand> E add(C entity, Class<E> clazz) {
        E referential = clazz.getDeclaredConstructor().newInstance();
        hashOperations.put(getParameterTypeName(), referential, referential.getClass().getName());
        return referential;
    }
    public String getParameterTypeName() {
        return ((Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).
                getActualTypeArguments()[0]).getTypeName();
    }

    @Override
    public void delete(E entity) {

    }

    @Override
    public E findById(String entityId) {
        return null;
    }
}
