package com.nas.driver.criteria;


import com.nas.driver.model.DriverStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public record DriverCriteria(String firstName, DriverStatus status) {

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public DriverStatus status() {
        return status;
    }
}
