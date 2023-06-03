package com.nas.driver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



@Entity
@Getter
@Setter
public class DriverStatus {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "ID")
    @EqualsAndHashCode.Include
    protected String id;

    private String status;

    public static DriverStatus create(String name){
        final DriverStatus driverStatus = new DriverStatus();

        driverStatus.status = name;

        return driverStatus;
    }
}
