package com.nas.customer.service.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
public class Customer extends BaseEntity{
    private String firstName;
    private String lastName;
    private String email;
}
