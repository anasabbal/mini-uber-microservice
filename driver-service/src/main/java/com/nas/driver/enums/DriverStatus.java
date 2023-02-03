package com.nas.driver.enums;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



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

    public static DriverStatus create(){
        final DriverStatus driverStatus = new DriverStatus();

        driverStatus.status = "AVAILABLE";

        return driverStatus;
    }
}
