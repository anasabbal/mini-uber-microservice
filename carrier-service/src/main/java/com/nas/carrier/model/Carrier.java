package com.nas.carrier.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Carrier extends BaseEntity{


    @OneToMany
    private Set<Job> jobs;


    public static Carrier create(){
        final Carrier carrier = new Carrier();

        return carrier;
    }
}
