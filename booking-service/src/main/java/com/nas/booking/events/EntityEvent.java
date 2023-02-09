package com.nas.booking.events;

import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

public class EntityEvent <T> implements ResolvableTypeProvider {

    private T entity;


    public EntityEvent(T entity) {
        this.entity = entity;
    }

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),ResolvableType.forInstance(entity));
    }
}