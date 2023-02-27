package com.nas.rating.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRating {
    private String id;

    public CustomerRating(String id) {
        this.id = id;
    }
}