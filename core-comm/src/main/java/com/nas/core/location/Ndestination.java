package com.nas.core.location;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ndestination {
    private CurrentLocation currentLocation;
    private GoLocation goLocation;
    private PriceEstimation priceEstimation;
}
