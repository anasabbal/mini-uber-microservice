package com.nas.core.dto;

import java.util.List;

public interface BaseMapper <DTO, E>{
    DTO toDto(E entity);
    List<DTO> toListDto(List<E> eList);
}
