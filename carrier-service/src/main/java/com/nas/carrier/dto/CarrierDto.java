package com.nas.carrier.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CarrierDto {
    private String id;
    private Set<JobDto> jobs;
}
