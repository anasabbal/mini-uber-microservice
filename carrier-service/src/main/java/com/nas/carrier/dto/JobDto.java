package com.nas.carrier.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class JobDto {
    private String id;
    private String description;
    private Set<ApplicationSubmitDto> applicationSubmitSet;
}
