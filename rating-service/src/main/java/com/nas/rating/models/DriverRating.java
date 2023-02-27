package com.nas.rating.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverRating {
    private String id;

    public DriverRating(String id) {
        this.id = id;
    }
}
