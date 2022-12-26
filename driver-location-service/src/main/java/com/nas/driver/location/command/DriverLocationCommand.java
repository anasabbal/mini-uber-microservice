package com.nas.driver.location.command;


import lombok.Getter;
import lombok.Setter;
import org.wololo.geojson.Feature;

@Getter
@Setter
public class DriverLocationCommand {
    private String name;
    private Feature feature;
}
