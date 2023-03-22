package com.nas.nuber.core;

public interface BaseService <T> {
    <P> T create(P payload, Class<T> eClass);

}
