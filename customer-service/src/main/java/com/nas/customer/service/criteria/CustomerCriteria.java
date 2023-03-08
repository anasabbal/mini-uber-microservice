package com.nas.customer.service.criteria;


import lombok.extern.jackson.Jacksonized;



@Jacksonized
public record CustomerCriteria(String firstName) {

    @Override
    public String firstName() {
        return firstName;
    }
}
