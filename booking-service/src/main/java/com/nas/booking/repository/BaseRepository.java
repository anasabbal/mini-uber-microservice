package com.nas.booking.repository;

import com.nas.booking.command.BookCommand;

import java.util.Map;

public interface BaseRepository <String, E>{

    <C extends BookCommand> E add(C entity, Class<E> clazz);
    void delete(E entity);
    E findById(String entityId);
}
