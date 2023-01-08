package com.nas.customer.service.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

import java.util.Set;


@Getter
@Setter
public class DriverSet {
    private ResponseEntity<Set<Driver>> drivers;

    public void setDrivers(ResponseEntity<Set<Driver>> drivers) {
        this.drivers = drivers;
    }
}
