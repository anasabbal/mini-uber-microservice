package com.nas.core.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NGenericMapper <E, DTO>{

    private final Class<E> entityClass;
    private final Class<DTO> dtoClass;

    public NGenericMapper(Class<E> entityClass, Class<DTO> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public DTO toDto(E entity) {
        DTO dto = null;
        try {
            dto = dtoClass.newInstance();
            for (Field dtoField : dtoClass.getDeclaredFields()) {
                dtoField.setAccessible(true);
                Field entityField = entityClass.getDeclaredField(dtoField.getName());
                entityField.setAccessible(true);
                dtoField.set(dto, entityField.get(entity));
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return dto;
    }
    public List<DTO> toDtoList(List<E> entityList) {
        List<DTO> dtoList = new ArrayList<>();
        for (E entity : entityList) {
            DTO dto = toDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
    public Set<DTO> toDtoSet(Set<E> entitySet){
        Set<DTO> dtoList = new HashSet<>();
        for (E entity : entitySet) {
            DTO dto = toDto(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
